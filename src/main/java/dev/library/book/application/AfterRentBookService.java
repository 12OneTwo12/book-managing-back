package dev.library.book.application;

import dev.library.book.DTO.BookDTO;
import dev.library.book.domain.Book;

public interface AfterRentBookService {

    public Book updateBookStateAfterRent(BookDTO bookDTO);
}
