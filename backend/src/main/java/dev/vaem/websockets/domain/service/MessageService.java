package dev.vaem.websockets.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.vaem.websockets.domain.entity.Message;

public interface MessageService {
    
    Message getMessage(long messageId);
    Page<Message> getAllMessagesInChat(long roomId, Pageable pageable);
    Message sendMessage(Message message);
    Message deleteMessage(long messageId);

}
