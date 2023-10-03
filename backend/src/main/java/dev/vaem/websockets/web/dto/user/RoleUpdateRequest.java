package dev.vaem.websockets.web.dto.user;

import dev.vaem.websockets.domain.entity.Role;
import jakarta.validation.constraints.NotBlank;

public record RoleUpdateRequest(@NotBlank Role.Name role) {
    
}
