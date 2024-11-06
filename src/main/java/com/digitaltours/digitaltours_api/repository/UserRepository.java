package com.digitaltours.digitaltours_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitaltours.digitaltours_api.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}