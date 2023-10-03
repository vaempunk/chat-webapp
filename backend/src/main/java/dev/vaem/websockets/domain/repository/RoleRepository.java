package dev.vaem.websockets.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import dev.vaem.websockets.domain.entity.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(Role.Name name);
}
