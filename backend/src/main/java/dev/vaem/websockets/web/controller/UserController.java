package dev.vaem.websockets.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.vaem.websockets.domain.entity.User;
import dev.vaem.websockets.domain.service.UserService;
import dev.vaem.websockets.web.dto.PropertyAvailabilityResponse;
import dev.vaem.websockets.web.dto.user.PasswordRequest;
import dev.vaem.websockets.web.dto.user.RoleUpdateRequest;
import dev.vaem.websockets.web.dto.user.UserRegistrationRequest;
import dev.vaem.websockets.web.dto.user.UserUpdateRequest;
import dev.vaem.websockets.web.dto.user.UsernameAvailabilityRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/me")
    public User getMe(@AuthenticationPrincipal long myId) {
        return userService.getUser(myId);
    }

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(
            @RequestBody @Valid UserRegistrationRequest userRequest) {
        var user = new User();
        user.setUsername(userRequest.username());
        user.setPassword(userRequest.password());
        return userService.registerUser(user);
    }

    @GetMapping("/signup/username-availability")
    public PropertyAvailabilityResponse isUsernameAvailable(
            @RequestBody @Valid UsernameAvailabilityRequest emailBody) {
        var available = userService.isUsernameAvailable(emailBody.username());
        return new PropertyAvailabilityResponse(available);
    }

    @PutMapping("/users/me")
    public User updateMe(
            @RequestBody @Valid UserUpdateRequest userRequest,
            @AuthenticationPrincipal long myId) {
        var user = new User();
        user.setId(myId);
        user.setUsername(userRequest.username());
        return userService.updateUser(user);
    }

    @PutMapping("/users/me/password")
    public void updatePassword(
            @RequestBody @Valid PasswordRequest passwordRequest,
            @AuthenticationPrincipal long myId) {
        userService.updateUserPassword(myId, passwordRequest.oldPassword(), passwordRequest.newPassword());
    }

    @PutMapping("/users/{id}/role")
    public void updateRole(
            @PathVariable("id") long userId,
            @RequestBody @Valid RoleUpdateRequest roleRequest) {
        userService.assignRoleToUser(userId, roleRequest.role());
    }

    @DeleteMapping("/users/me")
    public void deleteMe(@AuthenticationPrincipal long myId) {
        userService.deleteUser(myId);
    }

}
