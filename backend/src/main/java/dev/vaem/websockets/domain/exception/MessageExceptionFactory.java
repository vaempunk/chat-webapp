package dev.vaem.websockets.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface MessageExceptionFactory {
    static RuntimeException messageCooldownException() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "message cooldown");
    }
    static RuntimeException messageNotFoundException() {
        throw new RuntimeException();
    }
}
