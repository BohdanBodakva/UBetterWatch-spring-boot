package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.service.SupervisorService;
import ua.lviv.iot.ubetterwatch.service.UserService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserEntity> getAllSupervisors(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getAllSupervisors(@PathVariable Long id) throws IncorrectDataException {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public UserEntity saveSupervisor(@RequestBody UserEntity user) throws IncorrectDataException {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public UserEntity updateSupervisor(@PathVariable Long id, @RequestBody UserEntity user) throws IncorrectDataException {
        return userService.updateUserById(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteSupervisor(@PathVariable Long id) throws IncorrectDataException {
        userService.deleteUserById(id);
    }

    @DeleteMapping("/")
    public void deleteAllSupervisors(){
        userService.deleteAllUsers();
    }

}
