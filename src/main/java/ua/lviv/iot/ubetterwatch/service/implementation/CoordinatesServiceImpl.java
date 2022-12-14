package ua.lviv.iot.ubetterwatch.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ubetterwatch.entity.BraceletDataEntity;
import ua.lviv.iot.ubetterwatch.entity.CoordinatesEntity;
import ua.lviv.iot.ubetterwatch.entity.VoiceMessageEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.repository.CoordinatesRepository;
import ua.lviv.iot.ubetterwatch.service.CoordinatesService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CoordinatesServiceImpl implements CoordinatesService {

    private CoordinatesRepository coordinatesRepository;

    @Autowired
    public CoordinatesServiceImpl(CoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }

    @Override
    public List<CoordinatesEntity> getAllCoordinates() {
        return coordinatesRepository.findAll();
    }

    @Override
    public CoordinatesEntity getCoordinatesById(Long id) throws IncorrectDataException {
        CoordinatesEntity coordinates = coordinatesRepository.findById(id).orElse(null);

        if(coordinates == null){
            throw new IncorrectDataException("Bracelet data with id=" + id + " doesn't exist");
        }

        return coordinates;
    }

    @Override
    @Transactional
    public CoordinatesEntity saveCoordinates(CoordinatesEntity coordinates) throws IncorrectDataException {
        return coordinatesRepository.save(coordinates);
    }

    @Override
    @Transactional
    public CoordinatesEntity updateCoordinatesById(Long id, CoordinatesEntity coordinates) throws IncorrectDataException {
        CoordinatesEntity coordinatesToUpdate = coordinatesRepository.findById(id).orElse(null);

        if(coordinatesToUpdate == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        coordinatesToUpdate.setX(coordinates.getX());
        coordinatesToUpdate.setY(coordinates.getY());
        coordinatesToUpdate.setTime(coordinates.getTime());
        coordinatesToUpdate.setBraceletEntity(coordinates.getBraceletEntity());

        return coordinatesRepository.save(coordinatesToUpdate);
    }

    @Override
    @Transactional
    public void deleteCoordinatesById(Long id) throws IncorrectDataException {
        CoordinatesEntity coordinates = coordinatesRepository.findById(id).orElse(null);

        if(coordinates == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        coordinatesRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllCoordinates() {
        coordinatesRepository.deleteAll();
    }

    @Override
    public List<CoordinatesEntity> getCoordinatesByBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String supervisorUsername) {
        return coordinatesRepository.getCoordinatesByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, supervisorUsername);
    }

    @Override
    public CoordinatesEntity getCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String supervisorUsername, Long coordinatesId) throws IncorrectDataException {
        CoordinatesEntity coordinates = coordinatesRepository.getCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, supervisorUsername, coordinatesId)
                .orElse(null);

        if(coordinates == null){
            throw new IncorrectDataException("Coordinates doesn't exist");
        }

        return coordinates;
    }
}
