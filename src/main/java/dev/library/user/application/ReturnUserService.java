package dev.library.user.application;

import dev.library.user.domain.User;
import dev.library.user.dto.UserDTO;

public interface ReturnUserService {

    public UserDTO returnUserById(String userId);
    public User save(User afterCheckUser);

}
