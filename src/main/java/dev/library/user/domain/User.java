package dev.library.user.domain;

import dev.library.user.dto.UserDTO;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@DynamicInsert
public class  User {

    @EmbeddedId
    private UserId id;

    private String name;

    private String courseName;

    @Embedded
    private UserState userState;

    @Builder
    public User(UserId id, String name, String courseName, UserState userState) {
        this.id = id;
        this.name = name;
        this.courseName = courseName;
        this.userState = userState;
    }

    public User checkUserStateAndUpdate(Boolean checkReturnDeadline){

        // 아이디로 값을 찾게되면
        if (this.getUserState().checkCurrentRentedBooks()){
            // 유저가 대출한 책 갯수가 2개 이상일 경우 그대로 반환
            return this;
        } else {
            UserState newUserState = getUserState();
            if(this.getUserState().checkRentFreeDateIsEmpty()) { // RENT_FREE_DATE에 값이 있다면

                if (this.getUserState().rentFreeDateIsBeforeNow()) { // User의 대여 불가 기간이 오늘보다 전이라면
                    newUserState = new UserState(this.getUserState().getCurrentRentedBooks(),
                            this.getUserState().getRentFreeDate(),
                            true);
                }
            }
                if (checkReturnDeadline){ // 만약 유저가 대출중인 책이 반납 기한이 지났으면 rentable을 false로 설정 후 반납
                    newUserState = new UserState(this.getUserState().getCurrentRentedBooks(),
                                                 this.getUserState().getRentFreeDate(),
                                                     false);
                    System.out.println(newUserState);
                }

                User afterCheckUser = User.Request.toEntity(User.Request.builder().userId(this.getId())
                        .name(this.getName())
                        .courseName(this.getCourseName())
                        .userState(newUserState).build());

                if (this != afterCheckUser){
                    return afterCheckUser;
                }

            }
            return this;

        }



    public static User afterRentBookUpdateUser(UserDTO userDTO) {

        // the number of rented books + 1
        userDTO.setCurrentRentedBooks(userDTO.getCurrentRentedBooks() + 1);
        if (userDTO.getCurrentRentedBooks() >= 2) {
            userDTO.setRentable(false);
        }
        // userState update
        UserState newUserState = new UserState(userDTO.getCurrentRentedBooks(), userDTO.getRentFreeDate(),
                userDTO.getRentable());
        // userId
        UserId userId = new UserId(userDTO.getId());
        User updateUser = User.Request.toEntity(User.Request.builder().userId(userId)
                .name(userDTO.getName())
                .courseName(userDTO.getCourseName())
                .userState(newUserState)
                .build());

        return updateUser;

    }

    @Setter @Getter
    @ToString
    @Builder @AllArgsConstructor
    public static class Request {
        private UserId userId;
        @NotBlank
        private String name;
        @NotBlank
        private String courseName;
        private UserState userState;

//        public Request(UserId userId, String name, String courseName, UserState userState) {
//            this.userId = userId;
//            this.name = name;
//            this.courseName = courseName;
//            this.userState = userState;
//        }

        public static User toEntity(User.Request request) {
            return User.builder()
                    .id(request.getUserId())
                    .name(request.getName())
                    .courseName(request.getCourseName())
                    .userState(request.getUserState())
                    .build();
        }

    }
}
