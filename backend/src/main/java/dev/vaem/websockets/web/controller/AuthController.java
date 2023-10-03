package dev.vaem.websockets.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.vaem.websockets.domain.service.AuthService;
import dev.vaem.websockets.web.dto.auth.LoginRequest;
import dev.vaem.websockets.web.dto.auth.LoginResponse;
import dev.vaem.websockets.web.dto.error.ErrorResponse;
import dev.vaem.websockets.web.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/auth/token")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequest loginRequest) {
        var userOpt = authService.login(loginRequest.username(), loginRequest.password());

        if (userOpt.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("invalid username or password"));

        var user = userOpt.get();
        var token = jwtUtil.generateToken(String.valueOf(user.getId()), user.getRole().getName());
        return ResponseEntity.ok(new LoginResponse(token));
    }

}