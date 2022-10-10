package ua.lviv.iot.ubetterwatch.service.implementation;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.repository.SupervisorRepository;
import ua.lviv.iot.ubetterwatch.repository.UserRepository;
import ua.lviv.iot.ubetterwatch.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private SupervisorRepository supervisorRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SupervisorRepository supervisorRepository) {
        this.userRepository = userRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) throws IncorrectDataException {
        UserEntity user = userRepository.findById(id).orElse(null);

        if(user == null){
            throw new IncorrectDataException("User with id=" + id + " doesn't exist");
        }

        return user;
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUserById(Long id, UserEntity user) throws IncorrectDataException {
        UserEntity userToUpdate = userRepository.findById(id).orElse(null);

        if(userToUpdate == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setBirthDate(user.getBirthDate());
        userToUpdate.setStartUseDate(user.getStartUseDate());
        userToUpdate.setSupervisor(user.getSupervisor());

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) throws IncorrectDataException {
        UserEntity user = userRepository.findById(id).orElse(null);

        if(user == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}