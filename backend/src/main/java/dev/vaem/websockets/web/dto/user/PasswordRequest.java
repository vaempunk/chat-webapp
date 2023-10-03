package dev.vaem.websockets.web.dto.user;

import jakarta.validation.constraints.NotBlank;

public record PasswordRequest(
    @NotBlank String oldPassword,
    @NotBlank String newPassword) {
}