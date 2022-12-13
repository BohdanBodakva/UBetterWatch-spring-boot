//package ua.lviv.iot.ubetterwatch.service.implementation;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;
//import ua.lviv.iot.ubetterwatch.repository.SupervisorRepository;
//import ua.lviv.iot.ubetterwatch.security.SupervisorDetails;
//
//@Service
//public class SupervisorDetailsService implements UserDetailsService {
//    private final SupervisorRepository supervisorRepository;
//
//    @Autowired
//    public SupervisorDetailsService(SupervisorRepository supervisorRepository) {
//        this.supervisorRepository = supervisorRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SupervisorEntity supervisor = supervisorRepository.findSupervisorEntityByUsername(username)
//                .orElse(null);
//
//        if (supervisor == null) {
//            throw new UsernameNotFoundException("Supervisor with username "
//                    + username + "doesn't exist!");
//        }
//
//        return new SupervisorDetails(supervisor);
//    }
//
//}
