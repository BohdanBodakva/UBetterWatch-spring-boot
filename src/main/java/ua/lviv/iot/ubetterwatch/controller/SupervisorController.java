package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.iot.ubetterwatch.entity.BraceletEntity;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.security.SupervisorDetails;
import ua.lviv.iot.ubetterwatch.service.SupervisorService;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/supervisors")
public class SupervisorController {

    private SupervisorService supervisorService;

    @Autowired
    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping("/")
    public List<SupervisorEntity> getAllSupervisors(){
        return supervisorService.getAllSupervisors();
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}")
    public SupervisorEntity getSupervisorByUsername(@PathVariable String username) throws IncorrectDataException {
        return supervisorService.getSupervisorByUsername(username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users")
    public List<UserEntity> getUsersBySupervisorUsername(@PathVariable String username) throws IncorrectDataException {

        return supervisorService.getUsersBySupervisorUsername(username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{id}")
    public UserEntity getUserBySupervisorUserList(@PathVariable String username,
                                                         @PathVariable Long id) throws IncorrectDataException {

        return supervisorService.getUserByIdAndSupervisorUsername(id, username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{id}/bracelets")
    public List<BraceletEntity> getUserBraceletsByUserIdAndSupervisorUsername(@PathVariable String username,
                                                                    @PathVariable Long id) throws IncorrectDataException {

        supervisorService.getUserByIdAndSupervisorUsername(id, username);

        return supervisorService.getUserBraceletsByUserIdAndSupervisorUsername(id, username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{userId}/bracelets/{braceletId}")
    public BraceletEntity getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                    @PathVariable("userId") Long userId,
                                                                    @PathVariable("braceletId") String braceletId) throws IncorrectDataException {

        supervisorService.getUserByIdAndSupervisorUsername(userId, username);

        return supervisorService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{userId}/bracelets/{braceletId}/bracelet-data")
    public BraceletEntity getBraceletDataByBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                   @PathVariable("userId") Long userId,
                                                                                   @PathVariable("braceletId") String braceletId) throws IncorrectDataException {

        supervisorService.getUserByIdAndSupervisorUsername(userId, username);

        return supervisorService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
    }










    @PostMapping("/")
    public SupervisorEntity saveSupervisor(@RequestBody SupervisorEntity supervisor){
        return supervisorService.saveSupervisor(supervisor);
    }

//    @PutMapping("/{id}")
//    public SupervisorEntity updateSupervisorUsername(@PathVariable Long id, @RequestBody SupervisorEntity supervisor) throws IncorrectDataException {
//        return supervisorService.updateSupervisorById(id, supervisor);
//    }

    @DeleteMapping("/{username}")
    public void deleteSupervisorByUsername(@PathVariable String username) throws IncorrectDataException {
        supervisorService.deleteSupervisorByUsername(username);
    }

    @DeleteMapping("/")
    public void deleteAllSupervisors(){
        supervisorService.deleteAllSupervisors();
    }

}
