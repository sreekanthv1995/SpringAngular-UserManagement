package com.usermanagementproject.jwtsecurity.controller;

import com.usermanagementproject.jwtsecurity.dto.JwtAuthenticationResponse;
import com.usermanagementproject.jwtsecurity.dto.RefreshTokenRequest;
import com.usermanagementproject.jwtsecurity.dto.SignInRequest;
import com.usermanagementproject.jwtsecurity.dto.SignUpRequest;
import com.usermanagementproject.jwtsecurity.entity.User;
import com.usermanagementproject.jwtsecurity.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
