package ua.lviv.iot.ubetterwatch.service.implementation;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.repository.SupervisorRepository;
import ua.lviv.iot.ubetterwatch.repository.UserRepository;
import ua.lviv.iot.ubetterwatch.service.SupervisorService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    private SupervisorRepository supervisorRepository;
    private UserRepository userRepository;

    @Autowired
    public SupervisorServiceImpl(SupervisorRepository supervisorRepository, UserRepository userRepository) {
        this.supervisorRepository = supervisorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SupervisorEntity> getAllSupervisors(){
         return supervisorRepository.findAll();
    }

    @Override
    public SupervisorEntity getSuperVisorById(Long id) throws IncorrectDataException {
        SupervisorEntity supervisor = supervisorRepository.findById(id).orElse(null);

        if(supervisor == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        return supervisor;
    }

    @Override
    public List<UserEntity> getUsersBySupervisorId(Long id) {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .filter(user -> Objects.equals(user.getSupervisor().getId(), id))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SupervisorEntity saveSupervisor(SupervisorEntity supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @Override
    @Transactional
    public SupervisorEntity updateSupervisorById(Long id, SupervisorEntity supervisor) throws IncorrectDataException {
        SupervisorEntity supervisorToUpdate = supervisorRepository.findById(id).orElse(null);

        if(supervisorToUpdate == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        supervisorToUpdate.setFirstName(supervisor.getFirstName());
        supervisorToUpdate.setLastName(supervisor.getLastName());

        return supervisorRepository.save(supervisorToUpdate);
    }

    @Override
    @Transactional
    public void deleteSupervisorById(Long id) throws IncorrectDataException {
        SupervisorEntity supervisor = supervisorRepository.findById(id).orElse(null);

        if(supervisor == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        supervisorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllSupervisors() {
        supervisorRepository.deleteAll();
    }



}
