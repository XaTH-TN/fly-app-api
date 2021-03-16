package com.edu.fly.repository;

import com.edu.fly.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findById(Long id);

    Optional<UsersEntity> findByUsername(String username);
    Optional<UsersEntity> findByUsernameAndPassword(String username, String password);

}
