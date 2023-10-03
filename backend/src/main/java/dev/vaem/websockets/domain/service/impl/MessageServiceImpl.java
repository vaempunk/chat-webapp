package dev.vaem.websockets.domain.service.impl;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.vaem.websockets.domain.entity.Message;
import dev.vaem.websockets.domain.exception.ChatExceptionFactory;
import dev.vaem.websockets.domain.exception.MessageExceptionFactory;
import dev.vaem.websockets.domain.exception.UserExceptionFactory;
import dev.vaem.websockets.domain.repository.ChatRepository;
import dev.vaem.websockets.domain.repository.MessageRepository;
import dev.vaem.websockets.domain.repository.UserRepository;
import dev.vaem.websockets.domain.service.MessageService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Override
    public Message getMessage(long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(MessageExceptionFactory::messageNotFoundException);
    }

    @Override
    public Page<Message> getAllMessagesInChat(long chatId, Pageable pageable) {
        return messageRepository.findByChatId(chatId, pageable);
    }

    @Override
    public Message sendMessage(Message message) {
        var chat = chatRepository.findById(message.getChatId())
                .orElseThrow(ChatExceptionFactory::chatNotFoundException);
        var sender = userRepository.findById(message.getSenderId())
                .orElseThrow(UserExceptionFactory::userNotFoundException);
        if (!isCooldownExpiredForUserInChat(sender.getId(), chat.getCooldownMs()))
            throw MessageExceptionFactory.messageCooldownException();
        message.setId(0);
        message.setChat(chat);
        message.setSender(sender);
        message.setSentAt(Instant.now());
        return messageRepository.save(message);
    }

    private boolean isCooldownExpiredForUserInChat(long senderId, long chatCooldown) {
        var latestMessage = messageRepository
                .findBySenderId(senderId, PageRequest.of(0, 1, Sort.by("sentAt").descending()));
        if (latestMessage.isEmpty())
            return true;
        return latestMessage.getContent()
                .get(0)
                .getSentAt()
                .plusSeconds(chatCooldown)
                .isBefore(Instant.now());
    }

    @Override
    public Message deleteMessage(long messageId) {
        var message = messageRepository.findById(messageId)
                .orElseThrow(MessageExceptionFactory::messageNotFoundException);
        messageRepository.deleteById(messageId);
        return message;
    }

}
