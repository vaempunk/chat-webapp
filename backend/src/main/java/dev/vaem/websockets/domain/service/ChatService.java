package dev.vaem.websockets.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.vaem.websockets.domain.entity.Chat;

public interface ChatService {
    Chat getChat(long chatId);
    Page<Chat> getChats(Pageable pageable);
    Chat addChat(Chat chat);
    Chat updateChat(Chat chat);
    boolean isChatNameAvailable(String name);
    void deleteChat(long chatId);
}
