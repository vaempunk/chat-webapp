package dev.vaem.websockets.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.vaem.websockets.domain.entity.Chat;
import dev.vaem.websockets.domain.entity.User;
import dev.vaem.websockets.domain.service.ChatParticipantService;
import dev.vaem.websockets.domain.service.ChatService;
import dev.vaem.websockets.web.dto.PropertyAvailabilityResponse;
import dev.vaem.websockets.web.dto.chat.ChatCreateRequest;
import dev.vaem.websockets.web.dto.chat.ChatNameAvailabilityRequest;
import dev.vaem.websockets.web.dto.chat.ChatUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final ChatParticipantService chatParticipantService;

    @GetMapping("/chats/{id}")
    public Chat getChat(@PathVariable("id") long chatId) {
        return chatService.getChat(chatId);
    }

    @GetMapping("/chats")
    public Page<Chat> getChats(@RequestParam(name = "page", defaultValue = "0") @Min(0) int page) {
        return chatService.getChats(PageRequest.of(page, 10, Sort.by("name").ascending()));
    }

    @PostMapping("/chats")
    @ResponseStatus(HttpStatus.CREATED)
    public Chat addChat(@RequestBody @Valid ChatCreateRequest chatRequest) {
        var chat = new Chat();
        chat.setName(chatRequest.name());
        chat.setCooldownMs(chatRequest.cooldownMs());
        return chatService.addChat(chat);
    }

    @GetMapping("/chats/name-availability")
    public PropertyAvailabilityResponse isChatNameAvailable(
            @RequestBody @Valid ChatNameAvailabilityRequest chatRequest) {
        var available = chatService.isChatNameAvailable(chatRequest.name());
        return new PropertyAvailabilityResponse(available);
    }

    @PutMapping("/chats/{id}")
    public Chat updateChat(@PathVariable("id") long chatId, @RequestBody @Valid ChatUpdateRequest chatRequest) {
        var chat = new Chat();
        chat.setId(chatId);
        chat.setCooldownMs(chatRequest.cooldownMs());
        return chatService.updateChat(chat);
    }

    @DeleteMapping("/chats/{id}")
    public void deleteChat(@PathVariable("id") long chatId) {
        chatService.deleteChat(chatId);
    }

    @GetMapping("/chats/{id}/participants")
    public Page<User> getChatParticipants(@PathVariable("id") long chatId,
            @RequestParam(name = "page", defaultValue = "0") @Min(0) int page) {
        return chatParticipantService.getParticipants(chatId,
                PageRequest.of(page, 10, Sort.by("sender.username").ascending()));
    }

}
