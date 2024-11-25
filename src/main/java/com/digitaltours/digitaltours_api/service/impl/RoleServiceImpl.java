package com.digitaltours.digitaltours_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitaltours.digitaltours_api.dto.RoleDTO;
import com.digitaltours.digitaltours_api.exceptions.ResourceNotFoundException;
import com.digitaltours.digitaltours_api.mappers.RoleMapper;
import com.digitaltours.digitaltours_api.repository.RoleRepository;
import com.digitaltours.digitaltours_api.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    

    @Override
    public RoleDTO getRole(Long id) {
        return roleRepository.findById(id)
                .map(RoleMapper::mapRole)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrado"));
    }
}
