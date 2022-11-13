package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ubetterwatch.entity.VoiceMessageEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.service.VoiceMessagesService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/voice-messages")
public class VoiceMessageController {

    private VoiceMessagesService voiceMessagesService;

    @Autowired
    public VoiceMessageController(VoiceMessagesService voiceMessagesService) {
        this.voiceMessagesService = voiceMessagesService;
    }

    @GetMapping("/")
    public List<VoiceMessageEntity> getAllVoiceMessages(){
        return voiceMessagesService.getAllVoiceMessages();
    }

    @GetMapping("/{id}")
    public VoiceMessageEntity getVoiceMessageById(@PathVariable Long id) throws IncorrectDataException {
        return voiceMessagesService.getVoiceMessageById(id);
    }

    @PostMapping("/")
    public VoiceMessageEntity saveVoiceMessage(@RequestBody VoiceMessageEntity voiceMessage) throws IncorrectDataException {
        return voiceMessagesService.saveVoiceMessage(voiceMessage);
    }

    @PutMapping("/{id}")
    public VoiceMessageEntity updateVoiceMessage(@PathVariable Long id, @RequestBody VoiceMessageEntity voiceMessage) throws IncorrectDataException {
        return voiceMessagesService.updateVoiceMessageById(id, voiceMessage);
    }

    @DeleteMapping("/{id}")
    public void deleteVoiceMessageById(@PathVariable Long id) throws IncorrectDataException {
        voiceMessagesService.deleteVoiceMessageById(id);
    }

    @DeleteMapping("/")
    public void deleteAllVoiceMessages(){
        voiceMessagesService.deleteAllVoiceMessages();
    }
}
