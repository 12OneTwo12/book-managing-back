package dev.library.rental.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@ToString
@Getter
@NoArgsConstructor
public class RentalDate {

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    public RentalDate(LocalDate localDate){ this.rentDate = localDate; }

    public RentalDate(LocalDate localDate, LocalDate returnDate){
        this.rentDate = localDate;
        this.returnDate = returnDate;
    }

    public Boolean checkReturnDeadline(){
        LocalDate deadline = this.rentDate.plusDays(7);
        return LocalDate.now().isAfter(deadline);
    }

}
