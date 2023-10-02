package com.sungsu.project.sns.adapter.user.in.web;

import com.sungsu.project.sns.adapter.user.in.web.request.UserJoinRequest;
import com.sungsu.project.sns.adapter.user.in.web.request.UserLoginRequest;
import com.sungsu.project.sns.adapter.user.in.web.response.UserJoinResponse;
import com.sungsu.project.sns.adapter.user.in.web.response.UserLoginResponse;
import com.sungsu.project.sns.application.user.port.in.LoginUserUseCase;
import com.sungsu.project.sns.application.user.port.in.RegisterUserUsecase;
import com.sungsu.project.sns.common.response.CommonResponse;
import com.sungsu.project.sns.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final RegisterUserUsecase registerUserUsecase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/join")
    public CommonResponse<UserJoinResponse> register(@RequestBody UserJoinRequest userJoinRequest){
        return CommonResponse.success(UserJoinResponse.of(registerUserUsecase.join(userJoinRequest.getUserName(), userJoinRequest.getPassword())));
    }
    @PostMapping("/login")
    public CommonResponse<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        String token = loginUserUseCase.login(userLoginRequest);
        return CommonResponse.success(new UserLoginResponse(token));
    }


}
