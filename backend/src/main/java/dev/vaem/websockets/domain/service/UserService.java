package dev.vaem.websockets.domain.service;

import dev.vaem.websockets.domain.entity.Role;
import dev.vaem.websockets.domain.entity.User;

public interface UserService {
    User getUser(long userId);
    User registerUser(User user);
    boolean isUsernameAvailable(String username);
    User updateUser(User user);
    void updateUserPassword(long userId, String oldPassword, String newPassword);
    void deleteUser(long userId);
    void assignRoleToUser(long userId, Role.Name roleName);
}
