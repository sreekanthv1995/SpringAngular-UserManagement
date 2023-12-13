package com.usermanagementproject.jwtsecurity.services;

import com.usermanagementproject.jwtsecurity.dto.*;
import com.usermanagementproject.jwtsecurity.entity.User;

import java.util.List;
import java.util.Optional;

public interface AuthenticationService {

    public User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken (RefreshTokenRequest refreshTokenRequest);

    List<User> getAllUsers();
    Optional<User>findById(Integer id);

    public void deleteUser(Integer userId);

    User editUser(User user, EditDto updatedUser);


}
