package dev.library.user.presentation;

import dev.library.user.application.ReturnUserService;
import dev.library.user.dto.UserDTO;
import dev.library.user.dto.UserIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    ReturnUserService returnUserService;

    @PostMapping("/returnUser")
    public UserDTO returnUser(@RequestBody UserIdDTO userIdDTO){ return returnUserService.returnUserById(userIdDTO.getUserId()); }

}
