package com.digitaltours.digitaltours_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitaltours.digitaltours_api.entities.RoleEntity;

public interface RoleRepository  extends JpaRepository<RoleEntity, Long>{
    
}
