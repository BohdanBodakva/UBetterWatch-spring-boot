package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ubetterwatch.entity.BraceletEntity;
import ua.lviv.iot.ubetterwatch.entity.CoordinatesEntity;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.service.CoordinatesService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/coordinates")
public class CoordinatesController {

    private CoordinatesService coordinatesService;

    @Autowired
    public CoordinatesController(CoordinatesService coordinatesService) {
        this.coordinatesService = coordinatesService;
    }

    @GetMapping("/")
    public List<CoordinatesEntity> getAllCoordinates(){
        return coordinatesService.getAllCoordinates();
    }

    @GetMapping("/{id}")
    public CoordinatesEntity getCoordinatesById(@PathVariable Long id) throws IncorrectDataException {
        return coordinatesService.getCoordinatesById(id);
    }

    @PostMapping("/")
    public CoordinatesEntity saveSupervisor(@RequestBody CoordinatesEntity coordinates) throws IncorrectDataException {
        return coordinatesService.saveCoordinates(coordinates);
    }

    @PutMapping("/{id}")
    public CoordinatesEntity updateCoordinates(@PathVariable Long id, @RequestBody CoordinatesEntity coordinates) throws IncorrectDataException {
        return coordinatesService.updateCoordinatesById(id, coordinates);
    }

    @DeleteMapping("/{id}")
    public void deleteCoordinatesById(@PathVariable Long id) throws IncorrectDataException {
        coordinatesService.deleteCoordinatesById(id);
    }

    @DeleteMapping("/")
    public void deleteAllCoordinates(){
        coordinatesService.deleteAllCoordinates();
    }
}
