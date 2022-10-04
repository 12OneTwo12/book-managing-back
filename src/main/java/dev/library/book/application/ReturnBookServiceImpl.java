package dev.library.book.application;

import dev.library.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class ReturnBookServiceImpl implements ReturnBookService{

    private final BookRepository bookRepository;

    public ReturnBookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

}

