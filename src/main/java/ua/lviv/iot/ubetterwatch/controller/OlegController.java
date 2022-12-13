package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.ubetterwatch.entity.CoordinatesEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.service.CoordinatesService;

@RestController
@RequestMapping("/api/oleg")
public class OlegController {

    @Autowired
    private CoordinatesService coordinatesService;

    @PostMapping("/")
    public CoordinatesEntity oleg(@RequestBody CoordinatesEntity coordinates) throws IncorrectDataException {
        return coordinatesService.saveCoordinates(coordinates);
    }

}
