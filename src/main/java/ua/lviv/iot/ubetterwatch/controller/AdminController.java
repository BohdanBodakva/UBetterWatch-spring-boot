package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/")
    public String admin(){
        return "Welcome, admin!";
    }

}
