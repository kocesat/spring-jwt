package com.kocesat.project.springjwt.user.service.impl;

import com.kocesat.project.springjwt.user.model.User;
import com.kocesat.project.springjwt.user.repo.UserRepository;
import com.kocesat.project.springjwt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User: %s, not found", username)));
    }

    @Override
    public User getByUsername(String username) {
        return loadUserByUsername(username);
    }
}
