package com.usermanagementproject.jwtsecurity.services;

import com.usermanagementproject.jwtsecurity.dto.JwtAuthenticationResponse;
import com.usermanagementproject.jwtsecurity.dto.RefreshTokenRequest;
import com.usermanagementproject.jwtsecurity.dto.SignInRequest;
import com.usermanagementproject.jwtsecurity.dto.SignUpRequest;
import com.usermanagementproject.jwtsecurity.entity.User;

public interface AuthenticationService {

    public User signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken (RefreshTokenRequest refreshTokenRequest);
}
