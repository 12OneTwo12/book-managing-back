package dev.library.user.domain;

import lombok.*;

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
public class User {

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
