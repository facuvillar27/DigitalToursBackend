package com.digitaltours.digitaltours_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitaltours.digitaltours_api.dto.UserRegistrationRequestDTO;
import com.digitaltours.digitaltours_api.dto.UserRoleUpdateDTO;
import com.digitaltours.digitaltours_api.entities.UserEntity;
import com.digitaltours.digitaltours_api.exceptions.ResourceNotFoundException;
import com.digitaltours.digitaltours_api.mappers.UserMapper;
import com.digitaltours.digitaltours_api.repository.UserRepository;
import com.digitaltours.digitaltours_api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String registerUser(UserRegistrationRequestDTO request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setApellido(request.getApellido());
        user.setRole("ROLE_USER"); 

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserRoleUpdateDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::mapUser)
                .orElseThrow(() -> new ResourceNotFoundException("User no encontrado"));
    }

    @Override
    public String getRoleById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getRole(); // Devuelve el rol del usuario
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // @Override
    // public UserRoleUpdateDTO updateUser(Long id, UserRoleUpdateDTO userRoleUpdateDTO) {
    //     Optional<UserEntity> existingUser = userRepository.findById(id);
    //     if (existingUser.isPresent()) {
    //         UserEntity userEntity = existingUser.get();
    
    //         // Actualiza los campos del usuario
    //         userEntity.setName(userRoleUpdateDTO.getName());
    //         userEntity.setUsername(userRoleUpdateDTO.getUsername());
    //         userEntity.setApellido(userRoleUpdateDTO.getApellido());
    //         userEntity.setRole(userRoleUpdateDTO.getRole());
    //         userEntity.setPassword(userRoleUpdateDTO.getPassword());
    //         userEntity.setEmail(userRoleUpdateDTO.getEmail());
    
    //         // Guarda el usuario actualizado
    //         userEntity = userRepository.save(userEntity);
    //         return UserMapper.mapUser(userEntity);
    
    //     }
    //     throw new RuntimeException("Usuario no encontrado para actualizar");
    // }
    

}