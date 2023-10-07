package dev.vaem.websockets.domain.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.vaem.websockets.domain.entity.Role;
import dev.vaem.websockets.domain.entity.Role.Name;
import dev.vaem.websockets.domain.exception.factory.UserExceptionFactory;
import dev.vaem.websockets.domain.entity.User;
import dev.vaem.websockets.domain.repository.RoleRepository;
import dev.vaem.websockets.domain.repository.UserRepository;
import dev.vaem.websockets.domain.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(UserExceptionFactory::userNotFoundException);
        user.setPassword(null);
        return user;
    }

    @Override
    public User registerUser(User user) {
        if (!isUsernameAvailable(user.getUsername())) {
            throw UserExceptionFactory.userAlreadyExistsException();
        }
        user.setId(0);
        user.setRole(getRole(Role.Name.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        user.setPassword(null);
        return user;
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    private Role getRole(Role.Name roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(UserExceptionFactory::roleNotFoundException);
    }

    @Override
    public User updateUser(User user) {
        var existingUser = userRepository.findById(user.getId())
                .orElseThrow(UserExceptionFactory::userNotFoundException);
        existingUser.setUsername(user.getUsername());
        userRepository.save(user);
        existingUser.setPassword(null);
        return existingUser;
    }

    @Override
    public void updateUserPassword(long userId, String oldPassword, String newPassword) {
        var user = userRepository.findById(userId)
                .orElseThrow(UserExceptionFactory::userNotFoundException);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw UserExceptionFactory.passwordDoesNotMatchException();
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        if (!userRepository.existsById(userId)) {
            throw UserExceptionFactory.userNotFoundException();
        }
        userRepository.deleteById(userId);
    }

    @Override
    public void assignRoleToUser(long userId, Name roleName) {
        var user = userRepository.findById(userId)
                .orElseThrow(UserExceptionFactory::userNotFoundException);
        user.setRole(getRole(roleName));
        userRepository.save(user);
    }

}
