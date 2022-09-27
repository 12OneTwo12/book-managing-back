package dev.library.user.application;

import dev.library.dto.UserDTO;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.domain.UserState;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReturnUserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO returnUserById(String userId){

        Optional<User> user = userRepository.findById(UserId.of(userId));

        if (user.isEmpty()){ // 아이디로 값을 찾지 못하면 null return
            return null;
        } else { // 아이디로 값을 찾게되면
            User beforeCheckUser = user.get();
            // TODO : 유저가 대여중인 책 기한 지났는지 확인 후 지나면 대여 불가로 상태 변경 후 return
            // TODO : 유저 상태가 대여 불가라면 현재 RENT_FREE_DATE가 오늘 날짜를 지났는지 확인 후 안지났으면 그냥 반환
            if (beforeCheckUser.getUserState().getCurrentRentedBooks()>=2){
                // 유저가 대출한 책 갯수가 2개 이상일 경우 그대로 반환
                return UserDTO.entityToDTO(beforeCheckUser);
            } else {
                if(beforeCheckUser.getUserState().getRentFreeDate() != null){ // RENT_FREE_DATE에 값이 있다면
                    LocalDate rentFreeDate = beforeCheckUser.getUserState().getRentFreeDate();
                    UserState newUserState = beforeCheckUser.getUserState();
                    if(rentFreeDate.isBefore(LocalDate.now())){ // User의 대여 불가 기간이 오늘보다 전이라면
                        newUserState = new UserState(beforeCheckUser.getUserState().getCurrentRentedBooks(),
                                                     beforeCheckUser.getUserState().getRentFreeDate(),
                                                     true);
                    }

//                    if (true){ // 만약 유저가 대출중인 책이 반납 기한이 지났으면 rentable을 false로 설정 후 반납
//                        newUserState = new UserState(beforeCheckUser.getUserState().getCurrentRentedBooks(),
//                                                     beforeCheckUser.getUserState().getRentFreeDate(),
//                                                     false);
//                    }

                    User afterCheckUser = User.Request.toEntity(User.Request.builder().userId(beforeCheckUser.getId())
                                                                                              .name(beforeCheckUser.getName())
                                                                                              .courseName(beforeCheckUser.getCourseName())
                                                                                              .userState(newUserState).build());

                    if (beforeCheckUser != afterCheckUser){
                        User afterUpdateUser = userRepository.save(afterCheckUser);
                        return UserDTO.entityToDTO(afterUpdateUser);
                    }

                }
                return UserDTO.entityToDTO(beforeCheckUser);
            }

        }

    }

}
