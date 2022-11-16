package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.ubetterwatch.service.*;


@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final SupervisorService supervisorService;
    private final UserService userService;
    private final BraceletService braceletService;
    private final BraceletDataService braceletDataService;
    private final VoiceMessagesService voiceMessagesService;
    private final CoordinatesService coordinatesService;
    private final BraceletInStorageService braceletInStorageService;

    @Autowired
    public AdminController(SupervisorService supervisorService, UserService userService, BraceletService braceletService, BraceletDataService braceletDataService, VoiceMessagesService voiceMessagesService, CoordinatesService coordinatesService, BraceletInStorageService braceletInStorageService) {
        this.supervisorService = supervisorService;
        this.userService = userService;
        this.braceletService = braceletService;
        this.braceletDataService = braceletDataService;
        this.voiceMessagesService = voiceMessagesService;
        this.coordinatesService = coordinatesService;
        this.braceletInStorageService = braceletInStorageService;
    }

    @GetMapping("/")
    public String admin(){
        return "Welcome, admin!";
    }

}
