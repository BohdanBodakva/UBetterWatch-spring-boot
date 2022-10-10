package ua.lviv.iot.ubetterwatch.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ubetterwatch.entity.VoiceMessageEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.repository.VoiceMessagesRepository;
import ua.lviv.iot.ubetterwatch.service.VoiceMessagesService;

import java.util.List;

@Service
public class VoiceMessagesServiceImpl implements VoiceMessagesService {

    private VoiceMessagesRepository voiceMessagesRepository;

    @Autowired
    public VoiceMessagesServiceImpl(VoiceMessagesRepository voiceMessagesRepository) {
        this.voiceMessagesRepository = voiceMessagesRepository;
    }

    @Override
    public List<VoiceMessageEntity> getAllVoiceMessages() {
        return voiceMessagesRepository.findAll();
    }

    @Override
    public VoiceMessageEntity getVoiceMessageById(Long id) throws IncorrectDataException {
        VoiceMessageEntity voiceMessage = voiceMessagesRepository.findById(id).orElse(null);

        if(voiceMessage == null){
            throw new IncorrectDataException("Voice message data with id=" + id + " doesn't exist");
        }

        return voiceMessage;
    }

    @Override
    public VoiceMessageEntity saveVoiceMessage(VoiceMessageEntity voiceMessage) throws IncorrectDataException {
        return voiceMessagesRepository.save(voiceMessage);
    }

    @Override
    public VoiceMessageEntity updateVoiceMessageById(Long id, VoiceMessageEntity coordinates) throws IncorrectDataException {
        VoiceMessageEntity voiceMessageToUpdate = voiceMessagesRepository.findById(id).orElse(null);

        if(voiceMessageToUpdate == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        voiceMessageToUpdate.setFileName(coordinates.getFileName());
        voiceMessageToUpdate.setTime(coordinates.getTime());
        voiceMessageToUpdate.setBraceletEntity(coordinates.getBraceletEntity());

        return voiceMessagesRepository.save(voiceMessageToUpdate);
    }

    @Override
    public void deleteVoiceMessageById(Long id) throws IncorrectDataException {
        VoiceMessageEntity voiceMessage = voiceMessagesRepository.findById(id).orElse(null);

        if(voiceMessage == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        voiceMessagesRepository.deleteById(id);
    }

    @Override
    public void deleteAllVoiceMessages() {
        voiceMessagesRepository.deleteAll();
    }
}