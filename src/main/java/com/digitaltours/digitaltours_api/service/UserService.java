package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.UserRegistrationRequestDTO;
import com.digitaltours.digitaltours_api.dto.UserRoleUpdateDTO;
import com.digitaltours.digitaltours_api.entities.UserEntity;

public interface UserService {
    String registerUser(UserRegistrationRequestDTO request);
    List<UserEntity> getAllUsers();
    UserRoleUpdateDTO getUserById(Long id);
    String getRoleById(Long id);
    // void updateUserRole(Long id, String newRole);

    // UserRoleUpdateDTO updateUser(Long id, UserRoleUpdateDTO userRoleUpdateDTO);

    // void updateUser(UserRoleUpdateDTO userRoleUpdateDTO);
}
