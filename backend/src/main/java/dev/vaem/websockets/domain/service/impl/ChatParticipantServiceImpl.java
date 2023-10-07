package dev.vaem.websockets.domain.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.vaem.websockets.domain.entity.User;
import dev.vaem.websockets.domain.repository.ChatParticipantRepository;
import dev.vaem.websockets.domain.service.ChatParticipantService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatParticipantServiceImpl implements ChatParticipantService {
    
    private final ChatParticipantRepository chatParticipantRepository;
    
    @Override
    public Page<User> getParticipants(long chatId, Pageable pageable) {
        return chatParticipantRepository.findParticipants(chatId, pageable);
    }

}
