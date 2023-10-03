package dev.vaem.websockets.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import dev.vaem.websockets.domain.entity.User;

public interface ChatParticipantRepository extends Repository<User, Long> {

    @Query("select distinct m.sender from Message m where m.chat.id = ?1")
    Page<User> findParticipants(long chatId, Pageable pageable);

}
