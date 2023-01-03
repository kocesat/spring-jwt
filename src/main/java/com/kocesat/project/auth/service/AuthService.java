package com.kocesat.project.auth.service;

import com.kocesat.project.auth.model.AuthenticationResponse;
import com.kocesat.project.auth.model.LoginRequest;
import com.kocesat.project.auth.model.RegisterRequest;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);

}
