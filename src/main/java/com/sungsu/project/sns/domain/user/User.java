package com.sungsu.project.sns.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sungsu.project.sns.adapter.user.out.persistence.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Long id;
    private String userName;
    private String password;
    private UserRole role;
    private Timestamp regitsterdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;


    public void testFeater(){
        System.out.println("test freature");
    }

}
