package dev.library.admin;

import dev.library.admin.application.LoginAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminTest {

    @Autowired
    LoginAdminServiceImpl loginAdminService;

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
