package com.digitaltours.digitaltours_api.controller;

import com.digitaltours.digitaltours_api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.dto.AuthRequestDTO;
import com.digitaltours.digitaltours_api.dto.UserRegistrationRequestDTO;
import com.digitaltours.digitaltours_api.service.JwtService;
import com.digitaltours.digitaltours_api.service.CustomUserDetailsService;
import com.digitaltours.digitaltours_api.service.UserService;
import com.digitaltours.digitaltours_api.utils.Meta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Validated
@CrossOrigin
@RestController
@Slf4j
public class AuthController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final CustomUserDetailsService customUserDetailsService;  // Cambié esto a CustomUserDetailsService

    @Autowired
    private final UserService userService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
                          CustomUserDetailsService customUserDetailsService, UserService userService) {  // Cambié esto también
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;  // Cambié esto
        this.userService = userService;
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/v1/auth/signup")
    public ApiResponseDTO registerUser(@RequestBody UserRegistrationRequestDTO request) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, userService.registerUser(request));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(
                    new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/v1/auth/login")
    public ApiResponseDTO login(@RequestBody AuthRequestDTO authRequest) {
        try {
            // Log inicial con los datos recibidos
            logger.info("Intentando autenticar al usuario: {}", authRequest.getUsername());

            // Autenticar al usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            logger.info("Autenticación exitosa para el usuario: {}", authRequest.getUsername());

            // Obtener el CustomUserDetails
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
            logger.info("UserDetails cargado para el usuario: {}", authRequest.getUsername());

            // Acceder al UserEntity desde CustomUserDetails
            com.digitaltours.digitaltours_api.entities.UserEntity userEntity = ((CustomUserDetails) userDetails).getUserEntity();
            logger.info("UserEntity obtenido: Username={}, Role={}", userEntity.getUsername(), userEntity.getRole().getName());

            // Generar el token JWT
            String token = jwtService.generateToken(userEntity);
            logger.info("Token generado para el usuario: {}. Token={}", userEntity.getUsername(), token);

            // Crear y retornar la respuesta
            ApiResponseDTO response = new ApiResponseDTO(meta, token);
            logger.info("Respuesta creada con éxito para el usuario: {}", userEntity.getUsername());
            return response;

        } catch (Exception e) {
            // Registrar el error con su stack trace
            logger.error("Error durante el inicio de sesión para el usuario: {}. Mensaje: {}",
                    authRequest.getUsername(), e.getMessage(), e);
            return new ApiResponseDTO(
                    new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()),e
                    );
        }
    }


    @PostMapping("/v1/auth/resend-signup-email")
    public ApiResponseDTO resendSignupEmail(@RequestParam String email) {
        try {
            userService.resendSignupEmail(email);
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "OK", HttpStatus.OK.value()), "Correo de confirmación reenviado exitosamente");
        } catch (Exception e) {
            return new ApiResponseDTO(
                    new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }
}