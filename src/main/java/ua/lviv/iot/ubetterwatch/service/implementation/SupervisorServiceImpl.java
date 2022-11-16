package ua.lviv.iot.ubetterwatch.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ubetterwatch.entity.*;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.repository.*;
import ua.lviv.iot.ubetterwatch.service.SupervisorService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    private final SupervisorRepository supervisorRepository;

    @Autowired
    public SupervisorServiceImpl(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public List<SupervisorEntity> getAllSupervisors(){
         return supervisorRepository.findAll();
    }

    @Override
    @Transactional
    public SupervisorEntity saveSupervisor(SupervisorEntity supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @Override
    @Transactional
    public SupervisorEntity updateSupervisorByUsername(String username, SupervisorEntity supervisor) throws IncorrectDataException {
        SupervisorEntity supervisorToUpdate = supervisorRepository.findSupervisorEntityByUsername(username)
                .orElse(null);

        if(supervisorToUpdate == null){
            throw new IncorrectDataException("Supervisor with username=" + username + " doesn't exist");
        }

        supervisorToUpdate.setFirstName(supervisor.getFirstName());
        supervisorToUpdate.setLastName(supervisor.getLastName());

        return supervisorRepository.save(supervisorToUpdate);
    }



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
    @Transactional
    public SupervisorEntity updateSupervisorPasswordByUsername(String username, String previousPassword, String newPassword) throws IncorrectDataException {
        SupervisorEntity supervisorToUpdate = supervisorRepository.findSupervisorEntityByUsername(username)
                .orElse(null);

        if(supervisorToUpdate == null){
            throw new IncorrectDataException("Supervisor with username=" + username + " doesn't exist");
        }

        if(previousPassword.equals(supervisorToUpdate.getPassword())){
            supervisorToUpdate.setPassword(newPassword);
        } else {
            throw new IncorrectDataException("Previous password doesn't match");
        }

        return supervisorRepository.save(supervisorToUpdate);
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
