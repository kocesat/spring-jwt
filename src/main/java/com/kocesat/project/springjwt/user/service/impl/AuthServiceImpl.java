package com.kocesat.project.springjwt.user.service.impl;

import com.kocesat.project.springjwt.user.exception.UserNotFoundException;
import com.kocesat.project.springjwt.user.model.Credentials;
import com.kocesat.project.springjwt.user.model.User;
import com.kocesat.project.springjwt.user.model.view.AuthResponse;
import com.kocesat.project.springjwt.user.model.view.UserViewModel;
import com.kocesat.project.springjwt.user.repo.UserRepository;
import com.kocesat.project.springjwt.user.service.AuthService;
import com.kocesat.project.springjwt.user.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails getUserDetails(String token) {
        JwtParser parser = Jwts.parser().setSigningKey("my-app-kkkk");
        try {
            parser.parse(token);
            Claims claims = parser.parseClaimsJws(token).getBody();
            String userId = claims.getSubject();
            return userRepository.getById(new ObjectId(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthResponse authenticate(Credentials credentials) throws UserNotFoundException {
        final User user = userService.getByUsername(credentials.getUsername());
        boolean matches = passwordEncoder.matches(credentials.getPassword(), user.getPassword());
        if (!matches) {
            throw new UserNotFoundException("Username or password is wrong!");
        }
        UserViewModel userViewModel = UserViewModel.newInstance(user);
        String token = Jwts.builder()
                .setSubject(user.getId().toString())
                .signWith(SignatureAlgorithm.HS512, "my-app-kkkk")
                .compact();
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserViewModel(userViewModel);
        authResponse.setToken(token);
        return authResponse;
    }
}
