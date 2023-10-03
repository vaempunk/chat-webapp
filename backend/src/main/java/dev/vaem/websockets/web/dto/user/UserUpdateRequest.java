package dev.vaem.websockets.web.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank String username) {

}
