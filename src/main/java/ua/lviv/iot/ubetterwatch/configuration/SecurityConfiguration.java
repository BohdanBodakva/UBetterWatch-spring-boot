package ua.lviv.iot.ubetterwatch.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import ua.lviv.iot.ubetterwatch.service.implementation.SupervisorDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final SupervisorDetailsService supervisorDetailsService;

    @Autowired
    public SecurityConfiguration(SupervisorDetailsService supervisorDetailsService) {
        this.supervisorDetailsService = supervisorDetailsService;
    }

    // configs the authorization,
    // and it is config for all Spring Security in general
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                // requests
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/supervisors/**").hasRole("SUPERVISOR")
                .antMatchers("/api/auth/login", "/api/auth/registration", "/error").permitAll()
                .and()
                // login
                .formLogin().loginPage("/api/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/api/default/", true)
                .failureUrl("/api/auth/login?error")
                .and()
                // logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/auth/logout"))
                .logoutSuccessUrl("/api/auth/login");
    }

    // configs the authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(supervisorDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    // encode password
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
