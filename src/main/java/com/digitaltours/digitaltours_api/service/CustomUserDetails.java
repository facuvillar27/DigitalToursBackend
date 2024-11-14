package com.digitaltours.digitaltours_api.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.digitaltours.digitaltours_api.entities.UserEntity;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Asumimos que el rol es algo como "ROLE_USER", "ROLE_ADMIN", etc.
        return Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole()));
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes agregar lógica si es necesario
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Puedes agregar lógica si es necesario
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Puedes agregar lógica si es necesario
    }

    @Override
    public boolean isEnabled() {
        return true; // Puedes agregar lógica si es necesario
    }

    public Long getId() {
        return userEntity.getId(); // Devuelve el ID del usuario
    }

    public UserEntity getUserEntity() {
        return this.userEntity; // Devuelve el UserEntity original
    }
}