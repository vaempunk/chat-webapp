package dev.vaem.websockets.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.vaem.websockets.domain.entity.Message;
import dev.vaem.websockets.domain.service.MessageService;
import dev.vaem.websockets.web.dto.message.MessageDeleteResponse;
import dev.vaem.websockets.web.dto.message.MessageSendRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/chats/{chatId}/messages")
    public Page<Message> getMessages(@PathVariable("chatId") @Positive long chatId,
            @RequestParam(name = "page", defaultValue = "0") @Min(0) int page) {
        return messageService.getAllMessagesInChat(chatId, PageRequest.of(page, 10, Sort.by("sentAt").descending()));
    }

    @PostMapping("/chats/{chatId}/messages")
    public Message sendMessage(
            @RequestBody @Valid MessageSendRequest messageRequest,
            @PathVariable("chatId") @Positive long chatId,
            @AuthenticationPrincipal long myId) {
        var message = new Message();
        message.setText(messageRequest.text());
        message.setSenderId(myId);
        message.setChatId(chatId);
        message = messageService.sendMessage(message);
        simpMessagingTemplate.convertAndSend("/topic/%d/messages".formatted(chatId), message);
        return message;
    }

    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable("id") @Positive long messageId) {
        var message = messageService.deleteMessage(messageId);
        simpMessagingTemplate.convertAndSend("/topic/%d/messages/delete".formatted(message.getChat().getId()), new MessageDeleteResponse(messageId));

    }

}
