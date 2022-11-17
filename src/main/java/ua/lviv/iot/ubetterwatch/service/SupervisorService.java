package ua.lviv.iot.ubetterwatch.service;

import ua.lviv.iot.ubetterwatch.entity.*;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

import java.util.List;

public interface SupervisorService {
    List<SupervisorEntity> getAllSupervisors();
    SupervisorEntity getSupervisorByUsername(String username) throws IncorrectDataException;
    SupervisorEntity saveSupervisor(SupervisorEntity supervisor);
    SupervisorEntity updateSupervisorByUsername(String Username, SupervisorEntity supervisor) throws IncorrectDataException;
    void deleteSupervisorByUsername(String username) throws IncorrectDataException;
    void deleteAllSupervisors();

    SupervisorEntity updateSupervisorPasswordByUsername(String username, String previousPassword, String newPassword) throws IncorrectDataException;

}
