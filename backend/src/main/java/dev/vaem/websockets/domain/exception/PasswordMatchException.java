package dev.vaem.websockets.domain.exception;

public class PasswordMatchException extends RuntimeException {
    public PasswordMatchException() {
        super("password does not match");
    }
    
}
