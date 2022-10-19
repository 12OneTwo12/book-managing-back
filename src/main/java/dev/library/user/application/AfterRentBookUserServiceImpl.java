package dev.library.user.application;

import dev.library.user.domain.User;
import dev.library.user.dto.UserDTO;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AfterRentBookUserServiceImpl implements AfterRentBookUserService{

    @Autowired
    UserRepository userRepository;

    public User updateUserStateAfterRent(UserDTO userDTO) {

        User updateUser = User.afterRentBookUpdateUser(userDTO);
        userRepository.save(updateUser);

        return updateUser;

    }
}
