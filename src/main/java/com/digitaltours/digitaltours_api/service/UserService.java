package com.digitaltours.digitaltours_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitaltours.digitaltours_api.dto.UserRegistrationRequestDTO;
import com.digitaltours.digitaltours_api.entities.UserEntity;
import com.digitaltours.digitaltours_api.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(UserRegistrationRequestDTO request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setRole("ROLE_USER");

        //RoleEntity defaultRole = new RoleEntity();
        //defaultRole.setName("ROLE_USER"); // Asigna el rol por defecto

       // user.getRoles().add(defaultRole); // Agrega el rol al usuario

        userRepository.save(user);

        return "User registered successfully";
    }
}