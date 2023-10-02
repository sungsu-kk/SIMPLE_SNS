package com.sungsu.project.sns.application.user.service;

import com.sungsu.project.sns.adapter.user.in.web.request.UserJoinRequest;
import com.sungsu.project.sns.adapter.user.in.web.request.UserLoginRequest;
import com.sungsu.project.sns.application.user.port.in.LoginUserUseCase;
import com.sungsu.project.sns.application.user.port.in.RegisterUserUsecase;
import com.sungsu.project.sns.application.user.port.out.RegisterUserPort;
import com.sungsu.project.sns.application.user.port.out.SearchUserPort;
import com.sungsu.project.sns.common.exception.BaseException;
import com.sungsu.project.sns.common.exception.ErrorCode;
import com.sungsu.project.sns.common.utils.JwtTokenUtils;
import com.sungsu.project.sns.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements RegisterUserUsecase, LoginUserUseCase {
    private final SearchUserPort searchUserPort;
    private final RegisterUserPort registerUserPort;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    private final BCryptPasswordEncoder encoder;
    @Override
    @Transactional
    public User join(String userName, String password) {
        searchUserPort.checkRegisteredUserByUserName(userName);
        return registerUserPort.createUser(userName, encoder.encode(password));
    }

    @Override
    public String login(UserLoginRequest userLoginRequest) {
        User savedUser = searchUserPort.searchUserByUserName(userLoginRequest.getUserName());
        if(!encoder.matches(userLoginRequest.getPassword(), savedUser.getPassword())){
            throw new BaseException(ErrorCode.ERROR_INVALID_PASSWORD);
        }
        return JwtTokenUtils.generateAccessToken(userLoginRequest.getUserName(), secretKey, expiredTimeMs);
    }
}
