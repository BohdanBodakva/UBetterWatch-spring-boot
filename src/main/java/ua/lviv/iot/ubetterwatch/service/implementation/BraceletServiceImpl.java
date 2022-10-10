package ua.lviv.iot.ubetterwatch.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ubetterwatch.entity.*;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.repository.BraceletRepository;
import ua.lviv.iot.ubetterwatch.repository.CoordinatesRepository;
import ua.lviv.iot.ubetterwatch.repository.VoiceMessagesRepository;
import ua.lviv.iot.ubetterwatch.service.BraceletService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BraceletServiceImpl implements BraceletService {

    private BraceletRepository braceletRepository;
    private VoiceMessagesRepository voiceMessagesRepository;
    private CoordinatesRepository coordinatesRepository;

    @Autowired
    public BraceletServiceImpl(BraceletRepository braceletRepository,
                               VoiceMessagesRepository voiceMessagesRepository,
                               CoordinatesRepository coordinatesRepository) {
        this.braceletRepository = braceletRepository;
        this.voiceMessagesRepository = voiceMessagesRepository;
        this.coordinatesRepository = coordinatesRepository;
    }

    @Override
    public List<BraceletEntity> getAllBracelets() {
        return braceletRepository.findAll();
    }

    @Override
    public BraceletEntity getBraceletBySerialNumber(String serialNumber) throws IncorrectDataException {
        BraceletEntity bracelet = braceletRepository.findById(serialNumber).orElse(null);

        if(bracelet == null){
            throw new IncorrectDataException("Bracelet with serial number = " + serialNumber + " doesn't exist");
        }

        return bracelet;
    }

    @Override
    public List<CoordinatesEntity> getCoordinatesByBraceletSerialNumber(String serialNumber) {
        List<CoordinatesEntity> coordinates = coordinatesRepository.findAll();
        return coordinates.stream()
                .filter(user -> Objects.equals(user.getBraceletEntity().getSerialNumber(), serialNumber))
                .collect(Collectors.toList());
    }

    @Override
    public List<VoiceMessageEntity> getVoiceMessagesByBraceletSerialNumber(String serialNumber) {
        List<VoiceMessageEntity> voiceMessages = voiceMessagesRepository.findAll();
        return voiceMessages.stream()
                .filter(user -> Objects.equals(user.getBraceletEntity().getSerialNumber(), serialNumber))
                .collect(Collectors.toList());
    }

    @Override
    public BraceletEntity saveBracelet(BraceletEntity bracelet) throws IncorrectDataException {
        List<BraceletEntity> bracelets = braceletRepository.findAll();

        List<BraceletEntity> braceletsWithSameSerialNumber = bracelets.stream()
                .filter(br -> br.getSerialNumber().equals(bracelet.getSerialNumber())).toList();

        if(!braceletsWithSameSerialNumber.isEmpty()){
            throw new IncorrectDataException("Bracelet with serial number = " +
                    bracelet.getSerialNumber() + " already exists");
        }

        return braceletRepository.save(bracelet);
    }

    @Override
    public BraceletEntity updateBraceletBySerialNumber(String serialNumber, BraceletEntity bracelet) throws IncorrectDataException {
        BraceletEntity braceletToUpdate = braceletRepository.findById(serialNumber).orElse(null);

        if(braceletToUpdate == null){
            throw new IncorrectDataException("Bracelet with serial number =" + serialNumber + " doesn't exist");
        }

        braceletToUpdate.setUser(bracelet.getUser());
        braceletToUpdate.setBraceletData(bracelet.getBraceletData());

        return braceletRepository.save(braceletToUpdate);
    }

    @Override
    public void deleteBraceletBySerialNumber(String serialNumber) throws IncorrectDataException {
        BraceletEntity bracelet = braceletRepository.findById(serialNumber).orElse(null);

        if(bracelet == null){
            throw new IncorrectDataException("Bracelet with serial number =" + serialNumber + " doesn't exist");
        }

        braceletRepository.deleteById(serialNumber);
    }

    @Override
    public void deleteAllBracelets() {
        braceletRepository.deleteAll();
    }
}
