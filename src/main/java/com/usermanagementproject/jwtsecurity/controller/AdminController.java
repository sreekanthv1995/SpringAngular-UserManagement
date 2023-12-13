package com.usermanagementproject.jwtsecurity.controller;

import com.usermanagementproject.jwtsecurity.dto.EditDto;
import com.usermanagementproject.jwtsecurity.entity.User;
import com.usermanagementproject.jwtsecurity.repository.UserRepository;
import com.usermanagementproject.jwtsecurity.services.AuthenticationService;
import com.usermanagementproject.jwtsecurity.services.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationService authenticationService;
    private  final JWTService jwtService;
    private final UserRepository userRepository;



}
