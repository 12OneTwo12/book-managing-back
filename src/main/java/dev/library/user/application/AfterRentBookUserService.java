package dev.library.user.application;

import dev.library.user.domain.User;
import dev.library.user.dto.UserDTO;

public interface AfterRentBookUserService {

    public User updateUserStateAfterRent(UserDTO userDTO);
}
