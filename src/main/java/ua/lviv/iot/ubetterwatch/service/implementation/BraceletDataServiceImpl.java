package ua.lviv.iot.ubetterwatch.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ubetterwatch.entity.BraceletDataEntity;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.repository.BraceletDataRepository;
import ua.lviv.iot.ubetterwatch.service.BraceletDataService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BraceletDataServiceImpl implements BraceletDataService {

    private BraceletDataRepository braceletDataRepository;

    @Autowired
    public BraceletDataServiceImpl(BraceletDataRepository braceletDataRepository) {
        this.braceletDataRepository = braceletDataRepository;
    }

    @Override
    public List<BraceletDataEntity> getAllBraceletData() {
        return braceletDataRepository.findAll();
    }

    @Override
    public BraceletDataEntity getBraceletDataById(Long id) throws IncorrectDataException {
        BraceletDataEntity braceletData = braceletDataRepository.findById(id).orElse(null);

        if(braceletData == null){
            throw new IncorrectDataException("Bracelet data with id=" + id + " doesn't exist");
        }

        return braceletData;
    }

    @Override
    @Transactional
    public BraceletDataEntity saveBraceletData(BraceletDataEntity braceletData) throws IncorrectDataException {
        return braceletDataRepository.save(braceletData);
    }

    @Override
    @Transactional
    public BraceletDataEntity updateBraceletDataById(Long id, BraceletDataEntity braceletData) throws IncorrectDataException {
        BraceletDataEntity braceletDataToUpdate = braceletDataRepository.findById(id).orElse(null);

        if(braceletDataToUpdate == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        braceletDataToUpdate.setSimName(braceletData.getSimName());
        braceletDataToUpdate.setApn(braceletData.getApn());
        braceletDataToUpdate.setUsername(braceletData.getUsername());
        braceletDataToUpdate.setPassword(braceletData.getPassword());

        return braceletDataRepository.save(braceletDataToUpdate);
    }

    @Override
    @Transactional
    public void deleteBraceletDataById(Long id) throws IncorrectDataException {
        BraceletDataEntity braceletData = braceletDataRepository.findById(id).orElse(null);

        if(braceletData == null){
            throw new IncorrectDataException("Supervisor with id=" + id + " doesn't exist");
        }

        braceletDataRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllBraceletData() {
        braceletDataRepository.deleteAll();
    }

    @Override
    public BraceletDataEntity getBraceletDataByBraceletIdByUserIdAndSupervisorUsername(String braceletId, Long userId, String username) throws IncorrectDataException {
        BraceletDataEntity braceletData = braceletDataRepository.getBraceletDataByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username)
                .orElse(null);

        if(braceletData == null){
            throw new IncorrectDataException("Bracelet Data doesn't exist");
        }

        return braceletData;
    }
}
