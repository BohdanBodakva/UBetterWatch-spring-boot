package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ubetterwatch.entity.BraceletInStorageEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.service.BraceletInStorageService;

@CrossOrigin
@RestController
@RequestMapping("/api/bracelet-in-storage")
public class BraceletInStorageController {

    private BraceletInStorageService braceletInStorageService;

    @Autowired
    public BraceletInStorageController(BraceletInStorageService braceletInStorageService) {
        this.braceletInStorageService = braceletInStorageService;
    }

    @PostMapping("/")
    public BraceletInStorageEntity saveBraceletInStorage(@RequestBody BraceletInStorageEntity braceletInStorage){
        return braceletInStorageService.saveBraceletInStorage(braceletInStorage);
    }

    @DeleteMapping("/{serialNumber}")
    public void deleteBraceletInStorageById(@PathVariable String serialNumber) throws IncorrectDataException {
        braceletInStorageService.deleteBraceletInStorage(serialNumber);
    }

}
