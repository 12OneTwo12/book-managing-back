package dev.library.user.application;

import dev.library.user.dto.UserDTO;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturnUserServiceImpl implements ReturnUserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO returnUserById(String userId){

        Optional<User> user = userRepository.findById(UserId.of(userId));

        if (user.isEmpty()){
            return null;
        } else {
            User afterCheckUser = user.get().checkUserStateAndUpdate();
            if (afterCheckUser.getUserState().rentableIsChangeOrNot(user.get())){
                User updatedUser = userRepository.save(afterCheckUser);
                return UserDTO.entityToDTO(updatedUser);
            } else {
                return UserDTO.entityToDTO(afterCheckUser);
            }
        }

    }

    public User save(User afterCheckUser) {
        return userRepository.save(afterCheckUser);
    }
}
