package dev.vaem.websockets.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dev.vaem.websockets.domain.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query("select m from Message m where m.chat.id = ?1")
    Page<Message> findByChatId(long chatId, Pageable pageable);

    @Query("select m from Message m where m.sender.id = ?1")
    Page<Message> findBySenderId(long senderId, Pageable pageable);

    @Query("delete from Message m where m.chat.id = ?1")
    @Modifying
    void deleteByChatId(long chatId);

}
