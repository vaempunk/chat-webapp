package dev.vaem.websockets.web.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserRegistrationRequest(
        @NotBlank String username,
        @NotBlank String password) {

}
