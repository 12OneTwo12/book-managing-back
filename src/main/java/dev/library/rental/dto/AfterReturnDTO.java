package dev.library.rental.dto;

import dev.library.book.domain.Book;
import dev.library.book.domain.BookStateEnum;
import dev.library.book.dto.BookDTO;
import dev.library.rental.domain.Rental;
import dev.library.user.domain.User;
import dev.library.user.domain.UserState;
import dev.library.user.dto.UserDTO;
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
public class AfterReturnDTO {

    private List<BookDTO> bookDTO;
    private UserDTO userDTO;
    private List<RentalDTO> rentalDTO;

    public static AfterReturnDTO entityToDTO(Book bookResult, Rental rentalResult, User userResult) {
        AfterReturnDTO result = AfterReturnDTO.builder()
                             .userDTO(UserDTO.entityToDTO(userResult))
                             .build();

        List<BookDTO> bookList = new ArrayList<>();
        List<RentalDTO> rentalDTO = new ArrayList<>();
        bookList.add(BookDTO.entityToDTO(bookResult));
        rentalDTO.add(RentalDTO.entityToDTO(rentalResult));

        result.setBookDTO(bookList);
        result.setRentalDTO(rentalDTO);

        return result;
    }
}
