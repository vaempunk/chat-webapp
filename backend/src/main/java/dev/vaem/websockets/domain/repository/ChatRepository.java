package dev.vaem.websockets.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import dev.vaem.websockets.domain.entity.Chat;

public interface ChatRepository extends CrudRepository<Chat, Long> {

    Page<Chat> findAll(Pageable pageable);

    boolean existsByName(String name);
    
}
