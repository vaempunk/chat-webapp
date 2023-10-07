package dev.vaem.websockets.domain.exception.factory;

import dev.vaem.websockets.domain.exception.PasswordMatchException;
import dev.vaem.websockets.domain.exception.ResourceConflictException;
import dev.vaem.websockets.domain.exception.ResourceNotFoundException;

public interface UserExceptionFactory {
    static RuntimeException userNotFoundException() {
        return new ResourceNotFoundException("user not found");
    }

    static RuntimeException userAlreadyExistsException() {
        return new ResourceConflictException("user already exists");
    }

    static RuntimeException passwordDoesNotMatchException() {
        return new PasswordMatchException();
    }

    static RuntimeException roleNotFoundException() {
        return new ResourceNotFoundException("role not found");
    }

}