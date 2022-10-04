package dev.library.rental.dto;

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

}
