package ua.lviv.iot.ubetterwatch.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.iot.ubetterwatch.security.SupervisorDetails;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/api/default")
public class DefaultController {

    @GetMapping("/")
    public ModelAndView defaultRoutes(HttpServletRequest request){
        if (request.isUserInRole("ADMIN")) {
            return new ModelAndView("redirect:/api/admin/");
        }
        return new ModelAndView("redirect:/api/supervisors/" +
                ((SupervisorDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                        .getUsername());
    }


}
