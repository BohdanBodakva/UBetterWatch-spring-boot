package ua.lviv.iot.ubetterwatch.service;

import ua.lviv.iot.ubetterwatch.entity.BraceletEntity;
import ua.lviv.iot.ubetterwatch.entity.CoordinatesEntity;
import ua.lviv.iot.ubetterwatch.entity.VoiceMessageEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

import java.util.List;

public interface BraceletService {
    List<BraceletEntity> getAllBracelets();
    BraceletEntity getBraceletBySerialNumber(String serialNumber) throws IncorrectDataException;
    BraceletEntity saveBracelet(BraceletEntity bracelet) throws IncorrectDataException;
    BraceletEntity updateBraceletBySerialNumber(String serialNumber, BraceletEntity bracelet) throws IncorrectDataException;
    void deleteBraceletBySerialNumber(String serialNumber) throws IncorrectDataException;
    void deleteAllBracelets();

    List<BraceletEntity> getUserBraceletsByUserIdAndSupervisorUsername(Long id, String username);

    BraceletEntity getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String username) throws IncorrectDataException;
}
