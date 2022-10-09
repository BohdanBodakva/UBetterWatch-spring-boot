package ua.lviv.iot.ubetterwatch.controller;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.service.SupervisorService;

import javax.ws.rs.Path;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/supervisors")
public class SupervisorController {

    SupervisorService supervisorService;

    @Autowired
    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping("/")
    public List<SupervisorEntity> getAllSupervisors(){
        return supervisorService.getAllSupervisors();
    }

    @GetMapping("/{id}")
    public SupervisorEntity getAllSupervisors(@PathVariable Long id) throws IncorrectDataException {
        return supervisorService.getSuperVisorById(id);
    }

    @GetMapping("/{id}/users")
    public List<UserEntity> getUsersBySupervisorId(@PathVariable Long id) throws IncorrectDataException {
        return supervisorService.getUsersBySupervisorId(id);
    }

    @PostMapping("/")
    public SupervisorEntity saveSupervisor(@RequestBody SupervisorEntity supervisor){
        return supervisorService.saveSupervisor(supervisor);
    }

    @PutMapping("/{id}")
    public SupervisorEntity updateSupervisor(@PathVariable Long id, @RequestBody SupervisorEntity supervisor) throws IncorrectDataException {
        return supervisorService.updateSupervisorById(id, supervisor);
    }

    @DeleteMapping("/{id}")
    public void deleteSupervisor(@PathVariable Long id) throws IncorrectDataException {
        supervisorService.deleteSupervisorById(id);
    }

    @DeleteMapping("/")
    public void deleteAllSupervisors(){
        supervisorService.deleteAllSupervisors();
    }

}
