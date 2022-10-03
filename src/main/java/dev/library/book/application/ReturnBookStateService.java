package dev.library.book.application;

import dev.library.book.dto.BookDTO;

public interface ReturnBookStateService {

    BookDTO returnBookStateById(String bookId);


}
