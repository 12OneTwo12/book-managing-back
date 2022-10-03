package dev.library.user.application;

import dev.library.user.domain.Rental;
import dev.library.user.domain.User;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {

    @Autowired
    UserRepository userRepository;

    public void update(User updateStates) {

        User updateUser = updateStates.afterRentBookUpdateUser();

        userRepository.save(updateUser);
    }
}
