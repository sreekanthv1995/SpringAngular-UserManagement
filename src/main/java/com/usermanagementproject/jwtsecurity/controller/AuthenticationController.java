package com.usermanagementproject.jwtsecurity.controller;

import com.usermanagementproject.jwtsecurity.dto.*;
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
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JWTService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
        }
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

    @GetMapping("/users")
    public  ResponseEntity<List<User>>getAllUsers(){
        List<User> users = authenticationService.getAllUsers();
        return ResponseEntity.ok(users);
    }




    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id,HttpServletRequest request){
        Integer userId = Integer.parseInt(id);
        String token = request.getHeader("Authorization").split(" ")[1];

        if (jwtService.isTokenExpired(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        else {
            authenticationService.deleteUser(userId);
            return ResponseEntity.ok("Deleted");
        }

    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity<User> editUser(@RequestBody EditDto editDto,
                                         @PathVariable Integer id,
                                         HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization").split(" ")[1];
        System.out.println(id+"hjgfhgffghfgffg");

        if(jwtService.isTokenExpired(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<User> optionalUser = authenticationService.findById(id);
        if (optionalUser.isPresent()) {
            User editUser = optionalUser.get();
            editUser.setFirstName(editDto.getFirstName());
            editUser.setLastName(editDto.getLastName());
            editUser.setEmail(editDto.getEmail());
            User updatedUser = userRepository.save(editUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
