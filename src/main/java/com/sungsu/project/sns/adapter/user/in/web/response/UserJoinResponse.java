package com.sungsu.project.sns.adapter.user.in.web.response;

import com.sungsu.project.sns.domain.user.User;
import com.sungsu.project.sns.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse {

    private Long id;
    private String userName;
    private UserRole userRole;

    public static UserJoinResponse of(User user){
        return new UserJoinResponse(
                user.getId(),
                user.getUserName(),
                user.getRole()
        );
    }
}
