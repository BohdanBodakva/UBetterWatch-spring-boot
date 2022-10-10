package ua.lviv.iot.ubetterwatch.service;

import ua.lviv.iot.ubetterwatch.entity.BraceletDataEntity;
import ua.lviv.iot.ubetterwatch.entity.CoordinatesEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

import java.util.List;

public interface CoordinatesService {
    List<CoordinatesEntity> getAllCoordinates();
    CoordinatesEntity getCoordinatesById(Long id) throws IncorrectDataException;
    CoordinatesEntity saveCoordinates(CoordinatesEntity coordinates) throws IncorrectDataException;
    CoordinatesEntity updateCoordinatesById(Long id, CoordinatesEntity coordinates) throws IncorrectDataException;
    void deleteCoordinatesById(Long id) throws IncorrectDataException;
    void deleteAllCoordinates();
}
