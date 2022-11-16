package ua.lviv.iot.ubetterwatch.controller;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ubetterwatch.entity.*;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.service.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/supervisors")
public class SupervisorController {
    private final SupervisorService supervisorService;
    private final CoordinatesService coordinatesService;
    private final VoiceMessagesService voiceMessagesService;
    private final BraceletDataService braceletDataService;
    private final BraceletService braceletService;
    private final UserService userService;

    @Autowired
    public SupervisorController(SupervisorService supervisorService, CoordinatesService coordinatesService, VoiceMessagesService voiceMessagesService, BraceletDataService braceletDataService, BraceletService braceletService, UserService userService) {
        this.supervisorService = supervisorService;
        this.coordinatesService = coordinatesService;
        this.voiceMessagesService = voiceMessagesService;
        this.braceletDataService = braceletDataService;
        this.braceletService = braceletService;
        this.userService = userService;
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}")
    public SupervisorEntity getSupervisorByUsername(@PathVariable String username) throws IncorrectDataException {
        return supervisorService.getSupervisorByUsername(username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users")
    public List<UserEntity> getUsersBySupervisorUsername(@PathVariable String username) {
        return userService.getUsersBySupervisorUsername(username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{id}")
    public UserEntity getUserBySupervisorUserList(@PathVariable String username,
                                                  @PathVariable Long id) throws IncorrectDataException {
        return userService.getUserByIdAndSupervisorUsername(id, username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{id}/bracelets")
    public List<BraceletEntity> getUserBraceletsByUserIdAndSupervisorUsername(@PathVariable String username,
                                                                              @PathVariable Long id) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(id, username);
        return braceletService.getUserBraceletsByUserIdAndSupervisorUsername(id, username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{userId}/bracelets/{braceletId}")
    public BraceletEntity getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                   @PathVariable("userId") Long userId,
                                                                                   @PathVariable("braceletId") String braceletId) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        return braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{userId}/bracelets/{braceletId}/bracelet-data")
    public BraceletDataEntity getBraceletDataByBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                       @PathVariable("userId") Long userId,
                                                                                       @PathVariable("braceletId") String braceletId) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        return braceletDataService.getBraceletDataByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{userId}/bracelets/{braceletId}/voice-messages")
    public List<VoiceMessageEntity> getVoiceMessagesByBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                              @PathVariable("userId") Long userId,
                                                                                              @PathVariable("braceletId") String braceletId) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        return voiceMessagesService.getVoiceMessagesByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{userId}/bracelets/{braceletId}/voice-messages/{voiceMessageId}")
    public VoiceMessageEntity getVoiceMessagesByVoiceMessageIdBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                                      @PathVariable("userId") Long userId,
                                                                                                      @PathVariable("braceletId") String braceletId,
                                                                                                      @PathVariable("voiceMessageId") Long voiceMessageId) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        return voiceMessagesService.getVoiceMessagesByVoiceMessageIdBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username, voiceMessageId);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{userId}/bracelets/{braceletId}/coordinates")
    public List<CoordinatesEntity> getCoordinatesByBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                           @PathVariable("userId") Long userId,
                                                                                           @PathVariable("braceletId") String braceletId) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        return coordinatesService.getCoordinatesByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}/users/{userId}/bracelets/{braceletId}/coordinates/{coordinatesId}")
    public CoordinatesEntity getCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                                  @PathVariable("userId") Long userId,
                                                                                                  @PathVariable("braceletId") String braceletId,
                                                                                                  @PathVariable("coordinatesId") Long coordinatesId) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        return coordinatesService.getCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username, coordinatesId);
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping("/{username}")
    public SupervisorEntity updateSupervisorByUsername(@PathVariable String username, @RequestBody SupervisorEntity supervisor) throws IncorrectDataException {
        return supervisorService.updateSupervisorByUsername(username, supervisor);
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping("/{username}")
    public SupervisorEntity updateSupervisorPasswordByUsername(@PathVariable String username, String previousPassword, String newPassword) throws IncorrectDataException {
        return supervisorService.updateSupervisorPasswordByUsername(username, previousPassword, newPassword);
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/{username}")
    public void deleteSupervisorByUsername(@PathVariable String username) throws IncorrectDataException {
        supervisorService.deleteSupervisorByUsername(username);
    }


}
