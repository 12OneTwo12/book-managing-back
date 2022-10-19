package dev.library.user.presentation;

import dev.library.admin.application.CreateUserByAdminService;
import dev.library.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class createUserTest {

    @Autowired
    CreateUserByAdminService createUserByAdminService;

    @Test
    public void createUser(){

        User.Request request = User.Request.builder().name("정정일").courseName("인공지능 13기").build();

        String result = createUserByAdminService.saveUser(request);

        System.out.println(result);
    }

}
