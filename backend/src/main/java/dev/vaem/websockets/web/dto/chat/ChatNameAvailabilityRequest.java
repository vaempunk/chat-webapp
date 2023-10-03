package dev.vaem.websockets.web.dto.chat;

import jakarta.validation.constraints.NotBlank;

public record ChatNameAvailabilityRequest(@NotBlank String name) {
    
}
