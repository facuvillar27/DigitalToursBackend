package com.digitaltours.digitaltours_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.digitaltours.digitaltours_api.dto.UserRegistrationRequestDTO;
import com.digitaltours.digitaltours_api.dto.UserUpdateDTO;
import com.digitaltours.digitaltours_api.entities.UserEntity;
import com.digitaltours.digitaltours_api.mappers.UserMapper;
import com.digitaltours.digitaltours_api.repository.UserRepository;
import com.digitaltours.digitaltours_api.service.UserService;
import com.digitaltours.digitaltours_api.service.EmailService;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

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
            userEntity.setApellido(userUpdateDTO.getApellido());
            userEntity.setUsername(userUpdateDTO.getUsername());
            userEntity.setPassword(userUpdateDTO.getPassword());
            userEntity.setEmail(userUpdateDTO.getEmail());
            userEntity.setRole(userUpdateDTO.getRole());

            if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isEmpty()) {
                userEntity.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));}

            // Guarda el producto actualizado
            userEntity = userRepository.save(userEntity);
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