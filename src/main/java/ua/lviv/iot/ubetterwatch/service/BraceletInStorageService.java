package ua.lviv.iot.ubetterwatch.service;

import ua.lviv.iot.ubetterwatch.entity.BraceletInStorageEntity;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;

public interface BraceletInStorageService {
    BraceletInStorageEntity saveBraceletInStorage(BraceletInStorageEntity braceletInStorage);
    void deleteBraceletInStorage(String serialNumber) throws IncorrectDataException;
}
