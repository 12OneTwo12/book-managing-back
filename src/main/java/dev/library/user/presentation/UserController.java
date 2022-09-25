package dev.library.user.presentation;

import dev.library.user.application.CreateUserService;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

//    @Autowired
//    CreateUserService createUserService;
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserId> registerUser(@RequestBody User.Request request) {
        LOGGER.info("/users, registerUser() on Contoller");
        LOGGER.info("@RequestParam User.Request : {} ", request);

//        UserId registeredUserId = createUserService.saveUser(request);
        UserId registeredUserId = new UserId("u-g-0");
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUserId);
    }
}
