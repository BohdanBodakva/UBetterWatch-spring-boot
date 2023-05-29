package ua.lviv.iot.ubetterwatch.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.lviv.iot.ubetterwatch.service.implementation.SupervisorDetailsService;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final SupervisorDetailsService supervisorDetailsService;


    @Autowired
    public SecurityConfiguration(SupervisorDetailsService supervisorDetailsService) {
        this.supervisorDetailsService = supervisorDetailsService;

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/supervisors/**").hasRole("SUPERVISOR")
                .antMatchers("/api/auth/login", "/api/auth/registration", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/api/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/api/default/", true)
                .failureUrl("/api/auth/login?error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/auth/logout"))
                .logoutSuccessUrl("/api/auth/login");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(supervisorDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
