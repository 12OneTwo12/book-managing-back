package dev.library.user.dto;

import dev.library.rental.domain.Rental;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class UsersBookRentalInfoDTO {

    private String bookId;
    private String title;
    private LocalDate rentDate;

    public static UsersBookRentalInfoDTO rentalToDTO(Rental rental){
        return UsersBookRentalInfoDTO.builder().bookId(rental.getBook().getId().getId()).title(rental.getBook().getTitle()).rentDate(rental.getRentalDate().getRentDate()).build();
    }

}
