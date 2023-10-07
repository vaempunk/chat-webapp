package dev.vaem.websockets.domain.exception.factory;

import dev.vaem.websockets.domain.exception.MessageCooldownException;
import dev.vaem.websockets.domain.exception.ResourceNotFoundException;

public interface MessageExceptionFactory {
    static RuntimeException messageCooldownException() {
        throw new MessageCooldownException();
    }
    static RuntimeException messageNotFoundException() {
        throw new ResourceNotFoundException("message not found");
    }
}
