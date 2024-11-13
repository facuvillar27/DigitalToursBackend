package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.UserRegistrationRequestDTO;
import com.digitaltours.digitaltours_api.dto.UserUpdateDTO;
import com.digitaltours.digitaltours_api.entities.UserEntity;

public interface UserService {
    String registerUser(UserRegistrationRequestDTO request);
    List<UserEntity> getAllUsers();
    UserUpdateDTO getUser(Long id);
    UserUpdateDTO deleteUser(Long id);
    UserUpdateDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);
}
