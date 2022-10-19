package dev.library.rental.dto;

import dev.library.rental.domain.Rental;
import dev.library.user.domain.User;
import dev.library.user.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class RentalDTO {

    private String rentalId;
    private String user;
    private String book;
    private LocalDate rentalDate;

    public static RentalDTO entityToDTO(Rental rental){
        return RentalDTO.builder().rentalId(rental.getRentalId().toString()).user(rental.getUser().getId().getId()).book(rental.getBook().getId().getId()).rentalDate(rental.getRentalDate().getRentDate()).build();
    }

}
