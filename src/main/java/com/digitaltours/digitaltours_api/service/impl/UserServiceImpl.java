package com.digitaltours.digitaltours_api.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.digitaltours.digitaltours_api.dto.UserRegistrationRequestDTO;
import com.digitaltours.digitaltours_api.dto.UserUpdateDTO;
import com.digitaltours.digitaltours_api.entities.RoleEntity;
import com.digitaltours.digitaltours_api.entities.UserEntity;
import com.digitaltours.digitaltours_api.mappers.UserMapper;
import com.digitaltours.digitaltours_api.repository.RoleRepository;
import com.digitaltours.digitaltours_api.repository.UserRepository;
import com.digitaltours.digitaltours_api.service.EmailService;
import com.digitaltours.digitaltours_api.service.JwtService;
import com.digitaltours.digitaltours_api.service.UserService;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String registerUser(UserRegistrationRequestDTO request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLast_name(request.getLast_name());

        RoleEntity defaultRole = new RoleEntity();
        defaultRole.setId(2L); // ID del rol por defecto
        user.setRole(defaultRole);
        

        userRepository.save(user);

        // Enviar correo de confirmación
        try {
            emailService.sendConfirmationEmail(request.getEmail(), request.getUsername());
        } catch (MessagingException |UnsupportedEncodingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al enviar el correo de confirmación");
        }

        return "User registered successfully";
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserUpdateDTO getUser(final Long id) {
        final Optional<UserEntity> existUser = userRepository.findById(id);
        UserUpdateDTO userUpdateDTO = null;

        if (existUser.isPresent()) {
            userUpdateDTO = UserMapper.mapUser(existUser.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto No Encontrado");
        }
        return userUpdateDTO;
    }

    @Override
    public UserUpdateDTO deleteUser(final Long user) {
        UserUpdateDTO userEliminado = null;
        final Optional<UserEntity> existeUser = userRepository.findById(user);

        if (existeUser.isPresent()) {
            userEliminado = UserMapper.mapUser(existeUser.get());
            this.userRepository.delete(existeUser.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User No Encontrado");
        }
        return userEliminado;
    }

    @Override
    public UserUpdateDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        Optional<UserEntity> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            UserEntity userEntity = existingUser.get();

            // Actualiza los campos del producto
            userEntity.setName(userUpdateDTO.getName());
            userEntity.setLast_name(userUpdateDTO.getLast_name());
            userEntity.setUsername(userUpdateDTO.getUsername());
            userEntity.setEmail(userUpdateDTO.getEmail());
            // userEntity.setRole(userUpdateDTO.getRole());

            if (userUpdateDTO.getRole() != null && userUpdateDTO.getRole().getId() != null) {
                roleRepository.findById(userUpdateDTO.getRole().getId())
                    .ifPresent(userEntity::setRole); // Establece el rol al usuario
            }
            

            /*if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isEmpty()) {
                userEntity.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
            } else {
                userEntity.setPassword(existingUser.get().getPassword());
            }*/

            // Guarda el producto actualizado
            userEntity = userRepository.save(userEntity);
            String token =jwtService.generateToken(userEntity);
            return UserMapper.mapUser(userEntity);
        }
        throw new RuntimeException("User no encontrado para actualizar");
    }

    @Override
    public void resendSignupEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        try {
            emailService.sendConfirmationEmail(email, user.getUsername());
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al enviar el correo de confirmación");
        }
    }

}