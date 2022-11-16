package ua.lviv.iot.ubetterwatch.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.lviv.iot.ubetterwatch.entity.BraceletDataEntity;
import ua.lviv.iot.ubetterwatch.entity.CoordinatesEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

import java.util.List;
import java.util.Optional;

public interface CoordinatesService {
    List<CoordinatesEntity> getAllCoordinates();
    CoordinatesEntity getCoordinatesById(Long id) throws IncorrectDataException;
    CoordinatesEntity saveCoordinates(CoordinatesEntity coordinates) throws IncorrectDataException;
    CoordinatesEntity updateCoordinatesById(Long id, CoordinatesEntity coordinates) throws IncorrectDataException;
    void deleteCoordinatesById(Long id) throws IncorrectDataException;
    void deleteAllCoordinates();

    List<CoordinatesEntity> getCoordinatesByBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String supervisorUsername);

    CoordinatesEntity getCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String supervisorUsername, Long coordinatesId) throws IncorrectDataException;
}
