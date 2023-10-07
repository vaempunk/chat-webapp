package dev.vaem.websockets.web.dto.message;

import java.time.Instant;

import dev.vaem.websockets.web.dto.chat.ChatResponse;
import dev.vaem.websockets.web.dto.user.UserResponse;

public record MessageResponse(long id, String text, UserResponse sender, ChatResponse chat, Instant sentAt) {
    
}
