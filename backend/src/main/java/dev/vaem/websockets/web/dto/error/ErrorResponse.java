package dev.vaem.websockets.web.dto.error;

import java.util.List;

public record ErrorResponse(
    String error,
    List<ErrorDetails> details
) {
    public ErrorResponse(String error) {
        this(error, List.of());        
    }
}
