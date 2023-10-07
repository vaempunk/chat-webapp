package dev.vaem.websockets.domain.exception.factory;

import dev.vaem.websockets.domain.exception.ResourceConflictException;
import dev.vaem.websockets.domain.exception.ResourceNotFoundException;

public interface ChatExceptionFactory {
    static RuntimeException chatNotFoundException() {
        throw new ResourceNotFoundException("chat not found");
    }

    static RuntimeException chatAlreadyExistsException() {
        throw new ResourceConflictException("chat already exists");
    }
}
