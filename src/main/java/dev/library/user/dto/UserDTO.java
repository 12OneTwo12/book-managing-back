package dev.library.user.dto;

import dev.library.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class UserDTO {

    private String id;
    private String name;
    private String courseName;
    private Boolean rentable;
    private LocalDate rentFreeDate;
    private int currentRentedBooks;

    private List<UsersBookRentalInfoDTO> usersBookRentalInfoDTOS;

    public static UserDTO entityToDTO(User user){
        return UserDTO.builder().id(user.getId().getId())
                                .name(user.getName())
                                .courseName(user.getCourseName())
                                .rentable(user.getUserState().isRentable())
                                .rentFreeDate(user.getUserState().getRentFreeDate())
                                .currentRentedBooks(user.getUserState().getCurrentRentedBooks())
                                .build();
    }

}
