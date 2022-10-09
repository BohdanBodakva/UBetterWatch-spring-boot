package ua.lviv.iot.ubetterwatch.service;

import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id) throws IncorrectDataException;
    UserEntity saveUser(UserEntity supervisor) throws IncorrectDataException;
    UserEntity updateUserById(Long id, UserEntity supervisor) throws IncorrectDataException;
    void deleteUserById(Long id) throws IncorrectDataException;
    void deleteAllUsers();
}
