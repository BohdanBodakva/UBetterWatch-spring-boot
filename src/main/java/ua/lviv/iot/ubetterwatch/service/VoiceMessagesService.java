package ua.lviv.iot.ubetterwatch.service;

import org.springframework.data.repository.query.Param;
import ua.lviv.iot.ubetterwatch.entity.BraceletDataEntity;
import ua.lviv.iot.ubetterwatch.entity.VoiceMessageEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

import java.util.List;
import java.util.Optional;

public interface VoiceMessagesService {
    List<VoiceMessageEntity> getAllVoiceMessages();
    VoiceMessageEntity getVoiceMessageById(Long id) throws IncorrectDataException;
    VoiceMessageEntity saveVoiceMessage(VoiceMessageEntity coordinates) throws IncorrectDataException;
    VoiceMessageEntity updateVoiceMessageById(Long id, VoiceMessageEntity coordinates) throws IncorrectDataException;
    void deleteVoiceMessageById(Long id) throws IncorrectDataException;
    void deleteAllVoiceMessages();
    List<VoiceMessageEntity> getVoiceMessagesByBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String supervisorUsername);
    VoiceMessageEntity getVoiceMessagesByVoiceMessageIdBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String supervisorUsername, Long voiceMessageId) throws IncorrectDataException;



}
