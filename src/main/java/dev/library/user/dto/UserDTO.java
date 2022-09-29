package dev.library.user.dto;

import dev.library.rental.domain.Rental;
import dev.library.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private List<UsersBookRentalInfoDTO> usersBookRentalInfoDTO;

    public static UserDTO entityToDTO(User user){
        return UserDTO.builder().id(user.getId().getId())
                                .name(user.getName())
                                .courseName(user.getCourseName())
                                .rentable(user.getUserState().isRentable())
                                .rentFreeDate(user.getUserState().getRentFreeDate())
                                .currentRentedBooks(user.getUserState().getCurrentRentedBooks())
                                .build();
    }

    public void setUsersBookRentalInfoDTO(List<Rental> rentals){

        List<UsersBookRentalInfoDTO> result = new ArrayList<>();

        for (Rental rental : rentals){
            result.add(UsersBookRentalInfoDTO.rentalToDTO(rental));
        }
        this.usersBookRentalInfoDTO = result;
    }

}
