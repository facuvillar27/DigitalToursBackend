package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.UserRoleUpdateDTO;
import com.digitaltours.digitaltours_api.entities.UserEntity;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static UserRoleUpdateDTO mapUser (UserEntity user) {
        return MAPPER.map(user, UserRoleUpdateDTO.class);
    }

    public static UserEntity mapUserRoleUpdateDTO(UserRoleUpdateDTO userRoleUpdateDTO) {
        return MAPPER.map(userRoleUpdateDTO, UserEntity.class);
    }
    
}
