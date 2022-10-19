package dev.library.rental.application;

import dev.library.book.dto.BookIdDTO;
import dev.library.rental.dto.RentalDTO;
import dev.library.rental.dto.UserAndBookIdDTO;
import dev.library.user.dto.UserIdDTO;

public interface RentBookService {
    RentalDTO rentBook(String userId, String bookId);

}
