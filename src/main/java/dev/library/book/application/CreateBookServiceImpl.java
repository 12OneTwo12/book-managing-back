package dev.library.book.application;

import dev.library.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateBookServiceImpl implements CreateBookService{

    private final BookRepository bookRepository;

    public CreateBookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
