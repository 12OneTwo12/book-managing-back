package dev.library.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UsersBookRentalInfoDTO {

    private String title;
    private LocalDate rentDate;

}
