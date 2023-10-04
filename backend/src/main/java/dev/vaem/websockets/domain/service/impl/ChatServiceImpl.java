package dev.vaem.websockets.domain.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.vaem.websockets.domain.entity.Chat;
import dev.vaem.websockets.domain.exception.ChatExceptionFactory;
import dev.vaem.websockets.domain.repository.ChatRepository;
import dev.vaem.websockets.domain.repository.MessageRepository;
import dev.vaem.websockets.domain.service.ChatService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {
    
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @Override
    public Chat getChat(long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(ChatExceptionFactory::chatNotFoundException);
    }

    @Override
    public Page<Chat> getChats(Pageable pageable) {
        return chatRepository.findAll(pageable);
    }

    @Override
    public Chat addChat(Chat chat) {
        if (!isChatNameAvailable(chat.getName()))
            throw ChatExceptionFactory.chatAlreadyExistsException();
        chat.setId(0);
        return chatRepository.save(chat);
    }

    @Override
    public Chat updateChat(Chat chat) {
        var existingChat = chatRepository.findById(chat.getId())
                .orElseThrow(ChatExceptionFactory::chatNotFoundException);
        existingChat.setCooldownMs(chat.getCooldownMs());
        return chatRepository.save(chat);
    }

    @Override
    public boolean isChatNameAvailable(String name) {
        return !chatRepository.existsByName(name);
    }

    @Override
    @Transactional
    public void deleteChat(long chatId) {
        if (!chatRepository.existsById(chatId))
            throw ChatExceptionFactory.chatNotFoundException();
        messageRepository.deleteByChatId(chatId);
        chatRepository.deleteById(chatId);
    }

}
