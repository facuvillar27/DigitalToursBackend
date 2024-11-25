package com.digitaltours.digitaltours_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    
    private String name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    private RoleDTO role;

}