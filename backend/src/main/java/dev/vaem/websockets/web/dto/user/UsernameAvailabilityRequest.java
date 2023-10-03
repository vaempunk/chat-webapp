package dev.vaem.websockets.web.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UsernameAvailabilityRequest(
        @NotBlank String username) {
}