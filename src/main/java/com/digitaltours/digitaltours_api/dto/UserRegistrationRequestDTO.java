package com.digitaltours.digitaltours_api.dto;

import com.digitaltours.digitaltours_api.entities.RoleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequestDTO {
    
    private String username;
    private String name;
    private String last_name;
    private String password;
    private String email;
    private RoleEntity role;
}
