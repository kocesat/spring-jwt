package com.kocesat.project.springjwt.user.model.view;

import com.kocesat.project.springjwt.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserViewModel {
    private String userId;
    private String username;

    public static UserViewModel newInstance(User user) {
        return UserViewModel.builder()
                .userId(user.getId().toString())
                .username(user.getUsername())
                .build();
    }

}
