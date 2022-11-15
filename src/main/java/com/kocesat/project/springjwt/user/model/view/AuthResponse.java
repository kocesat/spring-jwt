package com.kocesat.project.springjwt.user.model.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private UserViewModel userViewModel;
}
