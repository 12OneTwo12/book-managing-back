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

    public User update(User updateUser) {

        Rental rental = new Rental();
        User newUser = rental.rentBook().getUser();

        return userRepository.save(newUser);
    }
}
