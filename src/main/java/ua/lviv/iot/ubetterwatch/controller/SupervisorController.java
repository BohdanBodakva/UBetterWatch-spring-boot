package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.ubetterwatch.entity.*;
import ua.lviv.iot.ubetterwatch.entity.change_password.ChangePassword;
import ua.lviv.iot.ubetterwatch.exception_handling.IncorrectDataException;
import ua.lviv.iot.ubetterwatch.security.SupervisorDetails;
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
    private final BraceletInStorageService braceletInStorageService;

    @Autowired
    public SupervisorController(SupervisorService supervisorService, CoordinatesService coordinatesService, VoiceMessagesService voiceMessagesService, BraceletDataService braceletDataService, BraceletService braceletService, UserService userService, BraceletInStorageService braceletInStorageService) {
        this.supervisorService = supervisorService;
        this.coordinatesService = coordinatesService;
        this.voiceMessagesService = voiceMessagesService;
        this.braceletDataService = braceletDataService;
        this.braceletService = braceletService;
        this.userService = userService;
        this.braceletInStorageService = braceletInStorageService;
    }

    // ===========================================================================================

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{username}")
    public SupervisorEntity getSupervisorByUsername(@PathVariable String username) throws IncorrectDataException {
        return supervisorService.getSupervisorByUsername(username);
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping("/{username}")
    public SupervisorEntity updateSupervisorByUsername(@PathVariable String username,
                                                       @RequestBody SupervisorEntity supervisor) throws IncorrectDataException {
        return supervisorService.updateSupervisorByUsername(username, supervisor);
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping("/{username}/update-password")
    public SupervisorEntity updateSupervisorPasswordByUsername(@PathVariable String username,
                                                               @RequestBody ChangePassword changePassword) throws IncorrectDataException {
        return supervisorService
                .updateSupervisorPasswordByUsername(username,
                                                    changePassword.getPreviousPassword(),
                                                    changePassword.getNewPassword());
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/{username}")
    public void deleteSupervisorByUsername(@PathVariable String username) throws IncorrectDataException {
        supervisorService.deleteSupervisorByUsername(username);
    }

    // ====================================================================================

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
    @PostMapping("/{username}/users/")
    public UserEntity saveUserBySupervisorUserList(@RequestBody UserEntity user,
                                                   @PathVariable String username) throws IncorrectDataException {
        user.setSupervisor(((SupervisorDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getSupervisor());
        return userService.saveUser(user);
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping("/{username}/users/{id}")
    public UserEntity updateUserBySupervisorUserList(@RequestBody UserEntity user,
                                                     @PathVariable Long id,
                                                     @PathVariable String username) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(id, username);
        user.setSupervisor(((SupervisorDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getSupervisor());
        return userService.updateUserById(id, user);
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/{username}/users/{id}")
    public void deleteUserBySupervisorUserList(@PathVariable Long id,
                                               @PathVariable String username) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(id, username);
        userService.deleteUserById(id);
    }


    // ==================================================================================================


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
    @PostMapping("/{username}/users/{userId}/bracelets/")
    public BraceletEntity saveUserBraceletByBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                    @PathVariable("userId") Long userId,
                                                                                    @RequestBody BraceletEntity bracelet) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        bracelet.setUser(userService.getUserById(userId));
        bracelet.setBraceletData(new BraceletDataEntity());
        return braceletService.saveBracelet(bracelet);
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping("/{username}/users/{userId}/bracelets/{braceletId}")
    public BraceletEntity updateUserBraceletByBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                      @PathVariable("userId") Long userId,
                                                                                      @RequestBody BraceletEntity bracelet,
                                                                                      @PathVariable("braceletId") String braceletId) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        return braceletService.updateBraceletBySerialNumber(braceletId, bracelet);
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/{username}/users/{userId}/bracelets/{braceletId}")
    public void deleteUserBraceletByBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                            @PathVariable("userId") Long userId,
                                                                            @PathVariable("braceletId") String braceletId) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        braceletService.deleteBraceletBySerialNumber(braceletId);
    }


    // ==============================================================================================


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
    @PostMapping("/{username}/users/{userId}/bracelets/{braceletId}/bracelet-data")
    public BraceletDataEntity saveBraceletDataByBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                        @PathVariable("userId") Long userId,
                                                                                        @PathVariable("braceletId") String braceletId,
                                                                                        @RequestBody BraceletDataEntity braceletData) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        return braceletDataService.saveBraceletData(braceletData);
    }


    // ================================================================================================


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
    @PostMapping("/{username}/users/{userId}/bracelets/{braceletId}/voice-messages/")
    public VoiceMessageEntity saveVoiceMessagesByVoiceMessageIdBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                                      @PathVariable("userId") Long userId,
                                                                                                      @PathVariable("braceletId") String braceletId,
                                                                                                      @RequestBody VoiceMessageEntity voiceMessage) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        voiceMessage.setBraceletEntity(braceletService.getBraceletBySerialNumber(braceletId));
        return voiceMessagesService.saveVoiceMessage(voiceMessage);
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping("/{username}/users/{userId}/bracelets/{braceletId}/voice-messages/{voiceMessageId}")
    public VoiceMessageEntity updateVoiceMessagesByVoiceMessageIdBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                                         @PathVariable("userId") Long userId,
                                                                                                         @PathVariable("braceletId") String braceletId,
                                                                                                         @PathVariable("voiceMessageId") Long voiceMessageId,
                                                                                                         @RequestBody VoiceMessageEntity voiceMessage) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        voiceMessagesService.getVoiceMessagesByVoiceMessageIdBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username, voiceMessageId);
        return voiceMessagesService.updateVoiceMessageById(voiceMessageId, voiceMessage);
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/{username}/users/{userId}/bracelets/{braceletId}/voice-messages/{voiceMessageId}")
    public void deleteVoiceMessagesByVoiceMessageIdBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                                         @PathVariable("userId") Long userId,
                                                                                                         @PathVariable("braceletId") String braceletId,
                                                                                                         @PathVariable("voiceMessageId") Long voiceMessageId) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        voiceMessagesService.getVoiceMessagesByVoiceMessageIdBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username, voiceMessageId);
        voiceMessagesService.deleteVoiceMessageById(voiceMessageId);
    }


    // =====================================================================================================


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
    @PostMapping("/{username}/users/{userId}/bracelets/{braceletId}/coordinates")
    public CoordinatesEntity saveCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                                       @PathVariable("userId") Long userId,
                                                                                                       @PathVariable("braceletId") String braceletId,
                                                                                                       @RequestBody CoordinatesEntity coordinates) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        coordinates.setBraceletEntity(braceletService.getBraceletBySerialNumber(braceletId));
        return coordinatesService.saveCoordinates(coordinates);
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping("/{username}/users/{userId}/bracelets/{braceletId}/coordinates/{coordinatesId}")
    public CoordinatesEntity updateCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                                         @PathVariable("userId") Long userId,
                                                                                                         @PathVariable("braceletId") String braceletId,
                                                                                                         @PathVariable("coordinatesId") Long coordinatesId,
                                                                                                         @RequestBody CoordinatesEntity coordinates) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        coordinatesService.getCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username, coordinatesId);
        return coordinatesService.updateCoordinatesById(coordinatesId, coordinates);
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/{username}/users/{userId}/bracelets/{braceletId}/coordinates/{coordinatesId}")
    public void deleteCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(@PathVariable("username") String username,
                                                                                           @PathVariable("userId") Long userId,
                                                                                           @PathVariable("braceletId") String braceletId,
                                                                                           @PathVariable("coordinatesId") Long coordinatesId) throws IncorrectDataException {
        userService.getUserByIdAndSupervisorUsername(userId, username);
        braceletService.getUserBraceletByBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username);
        coordinatesService.getCoordinatesByCoordinatesIdBraceletIdByUserIdAndSupervisorUsername(braceletId, userId, username, coordinatesId);
        coordinatesService.deleteCoordinatesById(coordinatesId);
    }




}
