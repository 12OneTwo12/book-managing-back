package dev.library.book.application;

import dev.library.book.DTO.BookDTO;
import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;
import dev.library.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturnBookStateServiceImpl implements ReturnBookStateService {

    private final BookRepository bookRepository;

    public ReturnBookStateServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public BookDTO returnBookStateById(String bookId){

        Optional<Book> book = bookRepository.findById(BookId.of(bookId));

        if (book.isEmpty()){
            return null;
        }
        Book bookState = book.get();
        return BookDTO.entityToDTO(bookState);

    }

}
