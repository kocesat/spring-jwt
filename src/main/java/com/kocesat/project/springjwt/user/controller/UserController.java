package com.kocesat.project.springjwt.user.controller;

import com.kocesat.project.springjwt.base.model.BaseResponse;
import com.kocesat.project.springjwt.user.exception.UserNotFoundException;
import com.kocesat.project.springjwt.user.model.Credentials;
import com.kocesat.project.springjwt.user.model.view.AuthResponse;
import com.kocesat.project.springjwt.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody Credentials credentials) throws UserNotFoundException {
        AuthResponse authResponse = authService.authenticate(credentials);
        return ResponseEntity.ok(BaseResponse.success(authResponse));
    }
}
