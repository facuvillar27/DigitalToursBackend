package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.RoleDTO;
import com.digitaltours.digitaltours_api.entities.RoleEntity;

public class RoleMapper {

    private RoleMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static RoleDTO mapRole (RoleEntity role) {
        return MAPPER.map(role, RoleDTO.class);
    }

    public static RoleEntity mapRoleDTO(RoleDTO roleDTO) {
        return MAPPER.map(roleDTO, RoleEntity.class);
    }
    
}
