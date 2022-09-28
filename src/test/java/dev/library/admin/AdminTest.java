package dev.library.admin;

import dev.library.dto.AdminDTO;
import dev.library.dto.AdminIdDTO;
import dev.library.user.application.CreateAdminService;
import dev.library.user.application.LoginAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminTest {

    @Autowired
    LoginAdminService loginAdminService;

//    @Autowired
//    CreateAdminService createAdminService;

//    @Test
//    public void createAdmin(){
//
//        AdminDTO adminDTO = new AdminDTO();
//
//        adminDTO.setAdminId("admin");
//        adminDTO.setAdminPw("1234");
//
//        AdminIdDTO adminIdDTO = createAdminService.createAdmin(adminDTO);
//
//        System.out.println(adminIdDTO);
//    }

//    @Test
//    public void loginAdmin(){
//        AdminDTO adminDTO = new AdminDTO();
//
//        adminDTO.setAdminId("admin");
//        adminDTO.setAdminPw("1234");
//
//        AdminIdDTO adminIdDTO = loginAdminService.loginAdmin(adminDTO);
//
//        System.out.println(adminIdDTO);
//
//    }

}
