package ua.lviv.iot.ubetterwatch.service;

import ua.lviv.iot.ubetterwatch.entity.BraceletDataEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

import java.util.List;

public interface BraceletDataService {
    List<BraceletDataEntity> getAllBraceletData();
    BraceletDataEntity getBraceletDataById(Long id) throws IncorrectDataException;
    BraceletDataEntity saveBraceletData(BraceletDataEntity braceletData) throws IncorrectDataException;
    BraceletDataEntity updateBraceletDataById(Long id, BraceletDataEntity braceletData) throws IncorrectDataException;
    void deleteBraceletDataById(Long id) throws IncorrectDataException;
    void deleteAllBraceletData();
    BraceletDataEntity getBraceletDataByBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String username) throws IncorrectDataException;

}
