package com.digitaltours.digitaltours_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.dto.UserUpdateDTO;
import com.digitaltours.digitaltours_api.entities.UserEntity;
import com.digitaltours.digitaltours_api.service.UserService;
import com.digitaltours.digitaltours_api.utils.Meta;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@SuppressWarnings("rawtypes")
@Validated
@CrossOrigin
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);


    @Operation(summary = "Mostrar todos los users usuarios", description = "Este endpoint permite mostrar todos los usuarios.")
    @GetMapping("/v1/users")
    public ApiResponseDTO getAllUsers() {
        try {
            List<UserEntity> users = userService.getAllUsers();
            ApiResponseDTO response = new ApiResponseDTO(meta, users);
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(
                    new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.INTERNAL_SERVER_ERROR.value()), e);
        }
    }

    @Operation(summary = "Mostrar un user usando su id", description = "Este endpoint permite mostrar un user usando su id.")
    @GetMapping("/v1/users/{id:[\\d]+}")
    public ApiResponseDTO getProduct(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, userService.getUser(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), "User not found");
        }
    }

    @Operation(summary = "Eliminar un user usando su id", description = "Este endpoint permite eliminar un user usando su id.")
    @DeleteMapping("/v1/users/{id:[\\d]+}")
    public ApiResponseDTO deleteUser(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, userService.deleteUser(id));
            return response;
        }catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Actualizar un user", description = "Este endpoint le permite a un administrador Actualizar un user.")
    @PutMapping("/v1/users/{id:[\\d]+}")
    public ApiResponseDTO updateProduct( @PathVariable Long id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, userService.updateUser(id, userUpdateDTO));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }
    
}