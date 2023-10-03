package dev.vaem.websockets.domain.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.vaem.websockets.domain.entity.User;
import dev.vaem.websockets.domain.repository.UserRepository;
import dev.vaem.websockets.domain.service.AuthService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> login(String username, String password) {
        var userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty())
            return Optional.empty();
        var user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword()))
            return Optional.empty();
        user.setPassword(null);
        return Optional.of(user);
    }

}
