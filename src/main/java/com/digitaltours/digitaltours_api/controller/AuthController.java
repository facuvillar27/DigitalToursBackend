package com.digitaltours.digitaltours_api.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.dto.AuthRequestDTO;
import com.digitaltours.digitaltours_api.dto.UserRegistrationRequestDTO;
import com.digitaltours.digitaltours_api.service.JwtService;
import com.digitaltours.digitaltours_api.service.ProductService;
import com.digitaltours.digitaltours_api.service.UserService;
import com.digitaltours.digitaltours_api.utils.Meta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

@Validated
@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final UserService userService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
            UserDetailsService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
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
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

            ApiResponseDTO response = new ApiResponseDTO(meta, jwtService.generateToken(userDetails));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(
                    new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), "Incorrect username or password");
        }

    }
}