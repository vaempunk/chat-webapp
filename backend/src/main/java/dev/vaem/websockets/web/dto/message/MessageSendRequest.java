package dev.vaem.websockets.web.dto.message;

import jakarta.validation.constraints.NotBlank;

public record MessageSendRequest(
    @NotBlank String text
) {

}
