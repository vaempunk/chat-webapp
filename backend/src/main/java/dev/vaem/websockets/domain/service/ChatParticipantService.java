package dev.vaem.websockets.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.vaem.websockets.domain.entity.User;


public interface ChatParticipantService {

    public Page<User> getParticipants(long chatId, Pageable pageable);

}
