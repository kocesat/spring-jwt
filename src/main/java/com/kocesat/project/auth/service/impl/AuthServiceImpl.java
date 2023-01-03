package com.kocesat.project.auth.service.impl;

import com.kocesat.project.auth.model.AuthenticationResponse;
import com.kocesat.project.auth.model.LoginRequest;
import com.kocesat.project.auth.model.RegisterRequest;
import com.kocesat.project.auth.service.AuthService;
import com.kocesat.project.repository.UserRepository;
import com.kocesat.project.service.JwtService;
import com.kocesat.project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }
    @Override
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
