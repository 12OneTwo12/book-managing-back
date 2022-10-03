package dev.library.book.application;

import dev.library.book.dto.BookDTO;
import dev.library.book.repository.BookRepository;
import dev.library.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AfterRentBookServiceImpl implements AfterRentBookService{

    @Autowired
    BookRepository bookRepository;

    public Book updateBookStateAfterRent(BookDTO bookDTO) {

        Book updateBook = Book.afterRentBookUpdateBook(bookDTO);
        bookRepository.save(updateBook);

        return updateBook;
    }
}
