package dev.library.user.presentation;

import dev.library.user.dto.UserDTO;
import dev.library.user.application.ReturnUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("UserReturn 클래스")
public class UserReturnTest {

    @Autowired
    ReturnUserService returnUserService;

    @Test
    public void userReturnTest(){
        UserDTO dto = returnUserService.returnUserById("u-13-1");
        System.out.println(dto);
    }

}
