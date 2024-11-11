package com.digitaltours.digitaltours_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.entities.UserEntity;
import com.digitaltours.digitaltours_api.service.UserService;
import com.digitaltours.digitaltours_api.utils.Meta;
import com.digitaltours.digitaltours_api.utils.ProductMessages;

import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(summary = "Mostrar todos los productos usuarios", description = "Este endpoint permite mostrar todos los usuarios.")
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

    @Operation(summary = "Mostrar un producto usando su id", description = "Este endpoint permite mostrar un producto usando su id.")
    @GetMapping("/v1/users/{id:[\\d]+}")
    public ApiResponseDTO getProduct(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, userService.getUserById(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), ProductMessages.PRODUCT_NOT_FOUND);
        }
    }


    @Operation(summary = "Obtener rol del usuario por ID", description = "Este endpoint permite obtener el rol de un usuario por su ID.")
    @GetMapping("/v1/users/role/{id}")
    public ApiResponseDTO getRoleById(@PathVariable Long id) {
        try {
            String role = userService.getRoleById(id);
            ApiResponseDTO response = new ApiResponseDTO(meta, role);
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(
                    new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.INTERNAL_SERVER_ERROR.value()), e);
        }
    }

    // @PutMapping("/v1/users/update/{id}")
    // public ResponseEntity<UserRoleUpdateDTO> updateUser(@PathVariable Long id, @RequestBody UserRoleUpdateDTO userRoleUpdateDTO) {
    //     UserRoleUpdateDTO updatedUser = userService.updateUser(id, userRoleUpdateDTO);
    //     if (updatedUser != null) {
    //         return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
}