package ua.lviv.iot.ubetterwatch.service;

import ua.lviv.iot.ubetterwatch.entity.BraceletEntity;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

import java.util.List;

public interface SupervisorService {
    List<SupervisorEntity> getAllSupervisors();
    SupervisorEntity getSupervisorByUsername(String username) throws IncorrectDataException;

    List<UserEntity> getUsersBySupervisorUsername(String username) throws IncorrectDataException;
    SupervisorEntity saveSupervisor(SupervisorEntity supervisor);
//    SupervisorEntity updateSupervisorByUsername(String Username, SupervisorEntity supervisor) throws IncorrectDataException;
    void deleteSupervisorByUsername(String username) throws IncorrectDataException;
    void deleteAllSupervisors();

    UserEntity getUserByIdAndSupervisorUsername(Long id, String username) throws IncorrectDataException;
    List<BraceletEntity> getUserBraceletsByUserIdAndSupervisorUsername(Long id, String username);

    BraceletEntity getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String username) throws IncorrectDataException;

}
