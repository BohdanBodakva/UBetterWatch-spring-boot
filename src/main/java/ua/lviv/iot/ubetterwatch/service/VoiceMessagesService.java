package ua.lviv.iot.ubetterwatch.service;

import ua.lviv.iot.ubetterwatch.entity.VoiceMessageEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

import java.util.List;

public interface VoiceMessagesService {
    List<VoiceMessageEntity> getAllVoiceMessages();
    VoiceMessageEntity getVoiceMessageById(Long id) throws IncorrectDataException;
    VoiceMessageEntity saveVoiceMessage(VoiceMessageEntity coordinates) throws IncorrectDataException;
    VoiceMessageEntity updateVoiceMessageById(Long id, VoiceMessageEntity coordinates) throws IncorrectDataException;
    void deleteVoiceMessageById(Long id) throws IncorrectDataException;
    void deleteAllVoiceMessages();
}
