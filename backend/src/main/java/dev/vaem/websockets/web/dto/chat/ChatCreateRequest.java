package dev.vaem.websockets.web.dto.chat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatCreateRequest(
        @NotBlank String name,
        @Min(0) @NotNull Long cooldownMs) {

}
