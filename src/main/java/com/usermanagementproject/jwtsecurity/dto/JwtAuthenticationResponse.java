package com.usermanagementproject.jwtsecurity.dto;

import com.usermanagementproject.jwtsecurity.entity.User;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;
    private User user;
}
