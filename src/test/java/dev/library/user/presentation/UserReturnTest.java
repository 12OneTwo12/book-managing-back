package dev.library.user.presentation;

import dev.library.user.dto.UserDTO;
import dev.library.user.application.ReturnUserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("UserReturn 클래스")
public class UserReturnTest {

    @Autowired
    ReturnUserServiceImpl returnUserService;

    @Test
    public void userReturnTest(){
        UserDTO dto = returnUserService.returnUserById("u-g-0");
        System.out.println(dto);
    }

}
