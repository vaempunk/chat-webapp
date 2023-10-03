package dev.vaem.websockets.domain.exception;

public interface UserExceptionFactory {
    static RuntimeException userNotFoundException() {
        return new RuntimeException("User not found");
    }

    static RuntimeException userAlreadyExistsException() {
        return new RuntimeException("User already exists");
    }

    static RuntimeException passwordDoesNotMatchException() {
        return new RuntimeException("Password does not match");
    }

    static RuntimeException roleNotFoundException() {
        return new RuntimeException("Role not found");
    }

}