package dev.library.rental.dto;

import dev.library.book.domain.Book;
import dev.library.book.domain.BookStateEnum;
import dev.library.rental.domain.Rental;
import dev.library.user.domain.User;
import dev.library.user.domain.UserState;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class AfterReturnDTO {

    private String user;
    private String book;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private BookStateEnum bookState;
    private UserState userState;

    public static AfterReturnDTO entityToDTO(Book bookResult, Rental rentalResult, User userResult) {
        return AfterReturnDTO.builder()
                             .user(userResult.getId().getId())
                             .book(bookResult.getId().getId())
                             .rentalDate(rentalResult.getRentalDate().getRentDate())
                             .returnDate(rentalResult.getRentalDate().getReturnDate())
                             .bookState(bookResult.getBookState().getBookStateEnum())
                             .userState(userResult.getUserState())
                             .build();
    }
}
