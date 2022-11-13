package ua.lviv.iot.ubetterwatch.util;


import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.service.implementation.SupervisorDetailsService;

@Component
public class SupervisorValidator implements Validator {
    private final SupervisorDetailsService supervisorDetailsService;

    @Autowired
    public SupervisorValidator(SupervisorDetailsService supervisorDetailsService) {
        this.supervisorDetailsService = supervisorDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SupervisorEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SupervisorEntity supervisor = (SupervisorEntity) target;
        try {
            supervisorDetailsService.loadUserByUsername(supervisor.getUsername());
        }catch (UsernameNotFoundException e){
            // good, because this username didn't exist before
            return;
        }
        errors.rejectValue("username", "", "Person with username " +
                supervisor.getUsername() + " already exists");
    }
}
