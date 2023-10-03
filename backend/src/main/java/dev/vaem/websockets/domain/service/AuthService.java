package dev.vaem.websockets.domain.service;

import java.util.Optional;

import dev.vaem.websockets.domain.entity.User;


public interface AuthService {
    
    Optional<User> login(String username, String password);

}
