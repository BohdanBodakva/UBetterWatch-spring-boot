package ua.lviv.iot.ubetterwatch.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ubetterwatch.entity.BraceletInStorageEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.repository.BraceletInStorageRepository;
import ua.lviv.iot.ubetterwatch.service.BraceletInStorageService;

import javax.transaction.Transactional;

@Service
public class BraceletInStorageServiceImpl implements BraceletInStorageService {

    private BraceletInStorageRepository braceletInStorageRepository;

    @Autowired
    public BraceletInStorageServiceImpl(BraceletInStorageRepository braceletInStorageRepository) {
        this.braceletInStorageRepository = braceletInStorageRepository;
    }

    @Override
    @Transactional
    public BraceletInStorageEntity saveBraceletInStorage(BraceletInStorageEntity braceletInStorage) {
        return braceletInStorageRepository.save(braceletInStorage);
    }

    @Override
    @Transactional
    public void deleteBraceletInStorage(String serialNumber) throws IncorrectDataException {
        BraceletInStorageEntity braceletInStorage = braceletInStorageRepository.findById(serialNumber).orElse(null);

        if(braceletInStorage == null){
            throw new IncorrectDataException("Bracelet with serial number = " + serialNumber + " doesn't exist");
        }

        braceletInStorageRepository.deleteById(serialNumber);
    }
}
