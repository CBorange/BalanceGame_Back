package com.saks.balance.backend.model;

import com.saks.balance.backend.entity.User;
import com.saks.balance.backend.entity.UserRole;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserRole> userRoles = user.getUserRoles();
        List<SimpleGrantedAuthority> authorities = user.getUserRoles().stream().map((userRole) -> {
            return new SimpleGrantedAuthority("ROLE_" + userRole.getRole().getAuthority().name());
        }).collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }
}
