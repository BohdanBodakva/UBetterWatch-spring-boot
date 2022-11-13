package ua.lviv.iot.ubetterwatch.service;

import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.entity.UserEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

import java.util.List;

public interface SupervisorService {
    List<SupervisorEntity> getAllSupervisors();
    SupervisorEntity getSuperVisorById(Long id) throws IncorrectDataException;

    List<UserEntity> getUsersBySupervisorId(Long id) throws IncorrectDataException;
    SupervisorEntity saveSupervisor(SupervisorEntity supervisor);
    SupervisorEntity updateSupervisorById(Long id, SupervisorEntity supervisor) throws IncorrectDataException;
    void deleteSupervisorById(Long id) throws IncorrectDataException;
    void deleteAllSupervisors();

}
