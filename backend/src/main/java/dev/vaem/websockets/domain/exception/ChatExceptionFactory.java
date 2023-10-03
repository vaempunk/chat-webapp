package dev.vaem.websockets.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ChatExceptionFactory {
    static RuntimeException chatNotFoundException() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "chat not found");
    }

    static RuntimeException chatAlreadyExistsException() {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "chat already exists");
    }
}
