package dev.vaem.websockets.web.util.mapper;

import dev.vaem.websockets.domain.entity.User;
import dev.vaem.websockets.web.dto.user.UserResponse;

public interface UserMapper {
    public static UserResponse entityToResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getRole());
    }
}
