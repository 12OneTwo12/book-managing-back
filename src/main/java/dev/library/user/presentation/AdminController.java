package dev.library.user.presentation;

import dev.library.dto.AdminDTO;
import dev.library.dto.AdminIdDTO;
import dev.library.user.application.CreateAdminService;
import dev.library.user.application.LoginAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("admin")
public class AdminController {

    @Autowired
    CreateAdminService createAdminService;

    @Autowired
    LoginAdminService loginAdminService;

    @PostMapping("/login_admin")
    public AdminIdDTO loginAdmin(@RequestBody @Valid AdminDTO adminDTO, Errors errors, HttpSession session){

        if (errors.hasErrors()){
            for(FieldError e : errors.getFieldErrors()){
                System.out.println(e.getDefaultMessage());
            }
            return null;
        } else {

            AdminIdDTO result = loginAdminService.loginAdmin(adminDTO);

            if (result != null){
                session.setAttribute("adminId",result.getAdminId());
                return result;
            } else {
                return null;
            }
        }

    }

    public AdminIdDTO signUpAdmin(@RequestBody @Valid AdminDTO adminDTO, Errors errors){

        if (errors.hasErrors()){
            for(FieldError e : errors.getFieldErrors()){
                System.out.println(e.getDefaultMessage());
            }
            return null;
        } else {
            return createAdminService.createAdmin(adminDTO);
        }

    }

}
