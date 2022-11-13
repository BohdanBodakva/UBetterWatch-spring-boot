package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.service.implementation.RegistrationService;
import ua.lviv.iot.ubetterwatch.util.SupervisorValidator;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final SupervisorValidator supervisorValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, SupervisorValidator supervisorValidator) {
        this.registrationService = registrationService;
        this.supervisorValidator = supervisorValidator;
    }

    @GetMapping("/login")
    public ModelAndView login () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView register (@ModelAttribute("supervisor") SupervisorEntity supervisor) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/register");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute("supervisor") @Valid SupervisorEntity supervisor,
                                     BindingResult bindingResult){

        supervisorValidator.validate(supervisor, bindingResult);

        if(bindingResult.hasFieldErrors() || bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("auth/register");
            return modelAndView;
        }

        registrationService.register(supervisor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login");
        return modelAndView;
    }

}
