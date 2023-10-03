package dev.vaem.websockets.domain.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class Message {
    
    @Id
    @GeneratedValue
    private long id;

    private String text;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Chat chat;

    private Instant sentAt;

    public long getSenderId() {
        if (sender == null)
            return 0;
        return sender.getId();
    }

    public void setSenderId(long senderId) {
        if (sender == null)
            sender = new User();
        sender.setId(senderId);
    }

    public long getChatId() {
        if (chat == null)
            return 0;
        return chat.getId();
    }

    public void setChatId(long chatId) {
        if (chat == null)
            chat = new Chat();
        chat.setId(chatId);
    }

}
