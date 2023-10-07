package dev.vaem.websockets.web.util.mapper;

import dev.vaem.websockets.domain.entity.Message;
import dev.vaem.websockets.web.dto.message.MessageResponse;

public interface MessageMapper {
    public static MessageResponse entityToResponse(Message message) {
        var sender = message.getSender();
        var chat = message.getChat();
        return new MessageResponse(message.getId(), message.getText(), UserMapper.entityToResponse(sender),
                ChatMapper.entityToResponse(chat), message.getSentAt());
    }
}
