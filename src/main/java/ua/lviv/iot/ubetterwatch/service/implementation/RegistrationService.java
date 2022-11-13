package ua.lviv.iot.ubetterwatch.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
import ua.lviv.iot.ubetterwatch.repository.SupervisorRepository;

import javax.transaction.Transactional;

@Service
public class RegistrationService {
    private final SupervisorRepository supervisorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(SupervisorRepository supervisorRepository, PasswordEncoder passwordEncoder) {
        this.supervisorRepository = supervisorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(SupervisorEntity supervisor){

        supervisor.setRole("ROLE_USER");
        supervisor.setPassword(passwordEncoder.encode(supervisor.getPassword()));

        supervisorRepository.save(supervisor);
    }
}
