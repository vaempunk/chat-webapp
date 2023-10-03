package dev.vaem.websockets.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.vaem.websockets.domain.entity.User;
import dev.vaem.websockets.domain.repository.ChatParticipantRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatParticipantService {

    private final ChatParticipantRepository chatParticipantRepository;
    
    public Page<User> getParticipants(long chatId, Pageable pageable) {
        return chatParticipantRepository.findParticipants(chatId, pageable);
    }

}
