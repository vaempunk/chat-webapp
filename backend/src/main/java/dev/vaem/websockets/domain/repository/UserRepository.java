package dev.vaem.websockets.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import dev.vaem.websockets.domain.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
