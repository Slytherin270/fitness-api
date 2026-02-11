package com.fitnessapp.auth.interfaceadapters;

import com.fitnessapp.auth.application.AuthUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/register")
    public AuthDtos.AuthResponse register(@Valid @RequestBody AuthDtos.RegisterRequest request) {
        AuthUseCase.AuthResult result = authUseCase.register(request.email(), request.password(), request.displayName());
        return toResponse(result);
    }

    @PostMapping("/login")
    public AuthDtos.AuthResponse login(@Valid @RequestBody AuthDtos.LoginRequest request) {
        AuthUseCase.AuthResult result = authUseCase.login(request.email(), request.password());
        return toResponse(result);
    }

    private AuthDtos.AuthResponse toResponse(AuthUseCase.AuthResult result) {
        return new AuthDtos.AuthResponse(
                result.token(),
                result.userAccount().id().toString(),
                result.userAccount().email(),
                result.userAccount().displayName());
    }
}
