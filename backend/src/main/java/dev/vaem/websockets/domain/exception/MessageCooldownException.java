package dev.vaem.websockets.domain.exception;

public class MessageCooldownException extends RuntimeException {
    public MessageCooldownException() {
        super("message cooldown");
    }
}
