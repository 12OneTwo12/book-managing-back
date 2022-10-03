package dev.library.user.application;

import dev.library.rental.domain.Rental;
import dev.library.rental.repository.RentalRepository;
import dev.library.user.dto.UserDTO;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReturnUserServiceImpl implements ReturnUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentalRepository rentalRepository;

    public UserDTO returnUserById(String userId){

        Optional<User> user = userRepository.findById(UserId.of(userId));

        if (user.isEmpty()){
            return null;
        } else {

            List<Rental> list = rentalRepository.findByUserNoReturn(user.get());
            Boolean checkDeadline;
            if(list.size()>1){
                checkDeadline = list.get(0).getRentalDate().checkReturnDeadline() || list.get(1).getRentalDate().checkReturnDeadline();
            } else if (list.size() == 1) {
                checkDeadline = list.get(0).getRentalDate().checkReturnDeadline();
            } else {
                checkDeadline = false;
            }

            User afterCheckUser = user.get().checkUserStateAndUpdate(checkDeadline);
            if (afterCheckUser.getUserState().rentableIsChangeOrNot(user.get())){
                User updatedUser = userRepository.save(afterCheckUser);

                UserDTO result = UserDTO.entityToDTO(updatedUser);

                result.setUsersBookRentalInfoDTO(list);

                return result;
            } else {

                UserDTO result = UserDTO.entityToDTO(afterCheckUser);

                result.setUsersBookRentalInfoDTO(list);

                return result;
            }
        }

    }

    public User save(User afterCheckUser) {
        return userRepository.save(afterCheckUser);
    }
}
