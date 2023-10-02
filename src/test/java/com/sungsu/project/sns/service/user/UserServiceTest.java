package com.sungsu.project.sns.service.user;

import com.sungsu.project.sns.adapter.user.out.persistence.UserEntity;
import com.sungsu.project.sns.adapter.user.out.persistence.UserJpaRepository;
import com.sungsu.project.sns.application.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserJpaRepository userJpaRepository;

    @MockBean
    private BCryptPasswordEncoder encoder;

    @Test
    void 회원가입이_정상인_경우(){
        String userName = "sungsu";
        String password = "1234";
        when(userJpaRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(userJpaRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));
        //when(encoder.encode(password)).thenReturn("1234");
        Assertions.assertDoesNotThrow(()->userService.join(userName,password));
    }

    @Test
    void 회원가입시_중복된_유저가_존재하는경우(){

    }


}
