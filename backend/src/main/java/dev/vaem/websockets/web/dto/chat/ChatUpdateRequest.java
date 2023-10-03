package dev.vaem.websockets.web.dto.chat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ChatUpdateRequest(
        @Min(0) @NotNull long cooldownMs) {

}
