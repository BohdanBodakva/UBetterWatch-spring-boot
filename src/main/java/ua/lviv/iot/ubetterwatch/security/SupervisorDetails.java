package ua.lviv.iot.ubetterwatch.security;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.lviv.iot.ubetterwatch.entity.SupervisorEntity;

import java.util.Collection;
import java.util.Collections;

@Getter
@ToString
public class SupervisorDetails implements UserDetails {
    private final SupervisorEntity supervisor;

    public SupervisorDetails(SupervisorEntity supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(supervisor.getRole()));
    }

    @Override
    public String getPassword() {
        return this.supervisor.getPassword();
    }

    @Override
    public String getUsername() {
        return this.supervisor.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
