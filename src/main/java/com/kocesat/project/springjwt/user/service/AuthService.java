package com.kocesat.project.springjwt.user.service;

import com.kocesat.project.springjwt.user.exception.UserNotFoundException;
import com.kocesat.project.springjwt.user.model.Credentials;
import com.kocesat.project.springjwt.user.model.view.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails getUserDetails(String jwt);
    AuthResponse authenticate(Credentials credentials) throws UserNotFoundException;
}
