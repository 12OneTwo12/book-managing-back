package dev.library.rental.application;

import dev.library.rental.dto.AfterReturnDTO;

public interface ReturnLentBookService {
    AfterReturnDTO returnLentBook(String bookId);
}
