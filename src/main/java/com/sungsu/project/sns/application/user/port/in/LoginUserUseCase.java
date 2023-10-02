package com.sungsu.project.sns.application.user.port.in;

import com.sungsu.project.sns.adapter.user.in.web.request.UserLoginRequest;

public interface LoginUserUseCase {

    String login(UserLoginRequest userLoginRequest);
}
