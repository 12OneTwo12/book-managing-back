package dev.library.rental.domain;

import lombok.Getter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@ToString
@Getter
public class RentalDate {

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    public RentalDate(LocalDate rentDate, LocalDate returnDate) {
        this.rentDate = rentDate;
        this.returnDate = this.rentDate.plusDays(7);
    }

    public Boolean checkReturnDeadline(){
        LocalDate deadline = this.rentDate.plusDays(7);
        return LocalDate.now().isAfter(deadline);
    }

}
