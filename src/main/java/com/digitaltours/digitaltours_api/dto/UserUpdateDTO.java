package com.digitaltours.digitaltours_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    
    private String name;
    private String apellido;
    private String username;
    private String password;
    private String email;
    private Integer role;

}