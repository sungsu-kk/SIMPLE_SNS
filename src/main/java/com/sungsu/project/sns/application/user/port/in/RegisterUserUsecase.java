package com.sungsu.project.sns.application.user.port.in;

import com.sungsu.project.sns.adapter.user.in.web.request.UserJoinRequest;
import com.sungsu.project.sns.domain.user.User;

public interface RegisterUserUsecase {
    User join(String userName, String password);
}
