package dev.library.Rental.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@ToString
@Getter
@Setter
public class RentalDate {

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

}
