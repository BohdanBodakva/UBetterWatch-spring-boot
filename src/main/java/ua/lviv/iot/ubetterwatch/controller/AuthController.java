//package ua.lviv.iot.ubetterwatch.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
//import ua.lviv.iot.ubetterwatch.service.implementation.RegistrationService;
//
//import javax.validation.Valid;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//    private final RegistrationService registrationService;
//
//    @Autowired
//    public AuthController(RegistrationService registrationService) {
//        this.registrationService = registrationService;
//    }
//
//    @GetMapping("/login")
//    public ModelAndView login () {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("auth/login");
//        return modelAndView;
//    }
//
//    @GetMapping("/registration")
//    public ModelAndView register (@ModelAttribute("supervisor") SupervisorEntity supervisor) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("auth/register");
//        return modelAndView;
//    }
//
//    @PostMapping("/registration")
//    public ModelAndView registration(@ModelAttribute("supervisor") SupervisorEntity supervisor){
//
//        registrationService.register(supervisor);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/api/auth/login");
//        return modelAndView;
//    }
//
//}
