package com.sungsu.project.sns.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sungsu.project.sns.adapter.user.in.web.request.UserJoinRequest;
import com.sungsu.project.sns.application.user.service.UserService;
import com.sungsu.project.sns.common.exception.BaseException;
import com.sungsu.project.sns.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @Test
    @WithAnonymousUser
    public void 회원가입_정상() throws Exception {
        String userName = "name";
        String password = "password";

        when(userService.join(userName, password)).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest("name", "password"))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입시_중복된_아이디입력_에러반환() throws Exception{
        //given
        String userName = "userName";
        String passWord="password";
        //when
        when(userService.join(userName,passWord)).thenThrow(BaseException.class);
        //then
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, passWord)))
                ).andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void 로그인() throws Exception {
        //given
        String userName = "userName";
        String passWord="password";
        //when
        when(userService.join(userName,passWord)).thenReturn(mock(User.class));
        //then
        mockMvc.perform(post("/api/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, passWord)))
                ).andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void 로그인시_회원가입이_안된경우_에러반환() throws Exception {
        //given
        String userName = "userName";
        String passWord="password";
        //when
        when(userService.join(userName,passWord)).thenReturn(mock(User.class));
        //then
        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, passWord)))
                ).andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void 로그인시_패스워드가_잘못입력된_경우_에러반환() throws Exception {
        //given
        String userName = "userName";
        String passWord="password";
        //when
        when(userService.join(userName,passWord)).thenReturn(mock(User.class));
        //then
        mockMvc.perform(post("/api/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, passWord)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

}
