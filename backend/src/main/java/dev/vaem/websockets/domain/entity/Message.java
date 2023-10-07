package dev.vaem.websockets.domain.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private String text;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Chat chat;

    private Instant sentAt;

    @Transient
    private long senderId;

    @Transient
    private long chatId;

}
