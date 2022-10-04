package dev.library.book.application;

import dev.library.book.dto.BookDTO;
import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;

import dev.library.book.repository.BookRepository;
import dev.library.exception.BookNullPointerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReturnBookStateServiceImpl implements ReturnBookStateService {

    private final BookRepository bookRepository;

    public ReturnBookStateServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO returnBookStateById(String bookId) {
        Optional<Book> book = bookRepository.findById(BookId.of(bookId));

        if (book.isEmpty()){
           throw new BookNullPointerException("해당 책을 찾을 수 없습니다");
        }
        Book bookState = book.get();
        return BookDTO.entityToDTO(bookState);
    }
}
