package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ubetterwatch.entity.BraceletDataEntity;
import ua.lviv.iot.ubetterwatch.entity.BraceletEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.service.BraceletDataService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/bracelet-data")
public class BraceletDataController {

    private BraceletDataService braceletDataService;

    @Autowired
    public BraceletDataController(BraceletDataService braceletDataService) {
        this.braceletDataService = braceletDataService;
    }

    @GetMapping("/")
    public List<BraceletDataEntity> getAllBraceletData(){
        return braceletDataService.getAllBraceletData();
    }

    @GetMapping("/{id}")
    public BraceletDataEntity getBraceletDataById(@PathVariable Long id) throws IncorrectDataException {
        return braceletDataService.getBraceletDataById(id);
    }

    @PostMapping("/")
    public BraceletDataEntity saveBraceletData(@RequestBody BraceletDataEntity braceletData) throws IncorrectDataException {
        return braceletDataService.saveBraceletData(braceletData);
    }

    @PutMapping("/{id}")
    public BraceletDataEntity updateBraceletData(@PathVariable Long id, @RequestBody BraceletDataEntity braceletData) throws IncorrectDataException {
        return braceletDataService.updateBraceletDataById(id, braceletData);
    }

    @DeleteMapping("/{id}")
    public void deleteBraceletDataById(@PathVariable Long id) throws IncorrectDataException {
        braceletDataService.deleteBraceletDataById(id);
    }

    @DeleteMapping("/")
    public void deleteAllBraceletData(){
        braceletDataService.deleteAllBraceletData();
    }
}
