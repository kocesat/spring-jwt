package com.kocesat.project.springjwt.user.service;

import com.kocesat.project.springjwt.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getByUsername(String username);
}
