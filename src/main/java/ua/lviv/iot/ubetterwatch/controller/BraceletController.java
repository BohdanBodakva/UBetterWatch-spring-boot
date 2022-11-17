package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ubetterwatch.entity.*;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.service.BraceletService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/bracelets")
public class BraceletController {

    private final BraceletService braceletService;

    @Autowired
    public BraceletController(BraceletService braceletService) {
        this.braceletService = braceletService;
    }

    @GetMapping("/")
    public List<BraceletEntity> getAllBracelets(){
        return braceletService.getAllBracelets();
    }

    @GetMapping("/{serialNumber}")
    public BraceletEntity getBraceletById(@PathVariable String serialNumber) throws IncorrectDataException {
        return braceletService.getBraceletBySerialNumber(serialNumber);
    }

    @GetMapping("/{serialNumber}/voice-messages")
    public List<VoiceMessageEntity> getVoiceMessagesByBraceletSerialNumber(@PathVariable String serialNumber) throws IncorrectDataException {
        return braceletService.getVoiceMessagesByBraceletSerialNumber(serialNumber);
    }

    @GetMapping("/{serialNumber}/coordinates")
    public List<CoordinatesEntity> getCoordinatesByBraceletSerialNumber(@PathVariable String serialNumber) throws IncorrectDataException {
        return braceletService.getCoordinatesByBraceletSerialNumber(serialNumber);
    }

    @PostMapping("/")
    public BraceletEntity saveSupervisor(@RequestBody BraceletEntity bracelet) throws IncorrectDataException {
        return braceletService.saveBracelet(bracelet);
    }

    @PutMapping("/{serialNumber}")
    public BraceletEntity updateSupervisor(@PathVariable String serialNumber, @RequestBody BraceletEntity bracelet) throws IncorrectDataException {
        return braceletService.updateBraceletBySerialNumber(serialNumber, bracelet);
    }

    @DeleteMapping("/{serialNumber}")
    public void deleteSupervisor(@PathVariable String serialNumber) throws IncorrectDataException {
        braceletService.deleteBraceletBySerialNumber(serialNumber);
    }

    @DeleteMapping("/")
    public void deleteAllSupervisors(){
        braceletService.deleteAllBracelets();
    }

}
