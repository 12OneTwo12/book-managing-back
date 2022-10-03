package dev.library.book.application;

import dev.library.book.repository.BookRepository;
import dev.library.user.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookService {

    @Autowired
    BookRepository bookRepository;

    public void update(Book updateState) {

        Book updateBook = updateState.afterRentBookUpdateBook();

        bookRepository.save(updateBook);
    }
}
