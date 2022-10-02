package dev.library.book.application;

import dev.library.book.DTO.BookDTO;

public interface ReturnBookStateService {

    BookDTO returnBookStateById(String BookId);


}
