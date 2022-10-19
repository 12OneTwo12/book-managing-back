package dev.library.user.application;

import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import dev.library.user.dto.UserDTO;

public interface ReturnUserService {

    public UserDTO returnUserById(String userId);
    public User save(User afterCheckUser);

}
