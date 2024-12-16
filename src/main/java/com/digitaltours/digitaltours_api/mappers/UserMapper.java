package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.UserUpdateDTO;
import com.digitaltours.digitaltours_api.entities.UserEntity;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static UserUpdateDTO mapUser (UserEntity user) {
        return MAPPER.map(user, UserUpdateDTO.class);
    }

    public static UserEntity UserUpdateDTO(UserUpdateDTO userUpdateDTO) {
        return MAPPER.map(userUpdateDTO, UserEntity.class);
    }
    
}
