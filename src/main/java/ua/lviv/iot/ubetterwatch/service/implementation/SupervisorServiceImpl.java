package ua.lviv.iot.ubetterwatch.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ubetterwatch.entity.BraceletEntity;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.repository.BraceletRepository;
import ua.lviv.iot.ubetterwatch.repository.SupervisorRepository;
import ua.lviv.iot.ubetterwatch.repository.UserRepository;
import ua.lviv.iot.ubetterwatch.service.SupervisorService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    private SupervisorRepository supervisorRepository;
    private UserRepository userRepository;
    private final BraceletRepository braceletRepository;

    @Autowired
    public SupervisorServiceImpl(SupervisorRepository supervisorRepository, UserRepository userRepository, BraceletRepository braceletRepository) {
        this.supervisorRepository = supervisorRepository;
        this.userRepository = userRepository;
        this.braceletRepository = braceletRepository;
    }

    @Override
    public List<SupervisorEntity> getAllSupervisors(){
         return supervisorRepository.findAll();
    }


    @Override
    public List<UserEntity> getUsersBySupervisorUsername(String username) {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .filter(user -> Objects.equals(user.getSupervisor().getUsername(), username))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SupervisorEntity saveSupervisor(SupervisorEntity supervisor) {
        return supervisorRepository.save(supervisor);
    }

//    @Override
//    @Transactional
//    public SupervisorEntity updateSupervisorById(String username, SupervisorEntity supervisor) throws IncorrectDataException {
//        SupervisorEntity supervisorToUpdate = supervisorRepository.findSupervisorEntityByUsername(username)
//                .orElse(null);
//
//        if(supervisorToUpdate == null){
//            throw new IncorrectDataException("Supervisor with username=" + username + " doesn't exist");
//        }
//
//        supervisorToUpdate.setFirstName(supervisor.getFirstName());
//        supervisorToUpdate.setLastName(supervisor.getLastName());
//
//        return supervisorRepository.save(supervisorToUpdate);
//    }

    @Override
    @Transactional
    public void deleteSupervisorByUsername(String username) throws IncorrectDataException {
        SupervisorEntity supervisor = supervisorRepository.findSupervisorEntityByUsername(username)
                .orElse(null);

        if(supervisor == null){
            throw new IncorrectDataException("Supervisor with username=" + username + " doesn't exist");
        }

        supervisorRepository.deleteSupervisorEntitiesByUsername(username);
    }

    @Override
    @Transactional
    public void deleteAllSupervisors() {
        supervisorRepository.deleteAll();
    }

    @Override
    public UserEntity getUserByIdAndSupervisorUsername(Long id, String username) throws IncorrectDataException {
        UserEntity user = userRepository.findUserEntityByIdAndSupervisorUsername(id, username)
                .orElse(null);

        if(user == null){
            throw new IncorrectDataException("User doesn't exist");
        }

        return user;
    }

    @Override
    public List<BraceletEntity> getUserBraceletsByUserIdAndSupervisorUsername(Long id, String username) {
        return braceletRepository.findUserBraceletsByUserIdAndSupervisorUsername(id, username);
    }

    @Override
    public BraceletEntity getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String username) throws IncorrectDataException {
        BraceletEntity bracelet = braceletRepository.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username)
                .orElse(null);

        if(bracelet == null){
            throw new IncorrectDataException("Bracelet doesn't exist");
        }

        return bracelet;
    }

    @Override
    public SupervisorEntity getSupervisorByUsername(String username) throws IncorrectDataException {
        SupervisorEntity supervisor = supervisorRepository.findSupervisorEntityByUsername(username)
                .orElse(null);

        if(supervisor == null){
            throw new IncorrectDataException("Supervisor with username=" + username + " doesn't exist");
        }

        return supervisor;
    }



}
