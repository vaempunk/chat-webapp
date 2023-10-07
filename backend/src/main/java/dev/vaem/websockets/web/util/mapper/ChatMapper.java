package dev.vaem.websockets.web.util.mapper;

import dev.vaem.websockets.domain.entity.Chat;
import dev.vaem.websockets.web.dto.chat.ChatResponse;

public interface ChatMapper {
    public static ChatResponse entityToResponse(Chat chat) {
        return new ChatResponse(chat.getId(), chat.getName(), chat.getCooldownMs());
    }
}
