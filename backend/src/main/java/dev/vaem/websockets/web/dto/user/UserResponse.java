package dev.vaem.websockets.web.dto.user;

import dev.vaem.websockets.domain.entity.Role;

public record UserResponse(long id, String username, Role role) {
    
}
