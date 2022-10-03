package dev.library.book.application;

import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;
import dev.library.book.domain.BookStateEnum;
import dev.library.book.presentation.request.BookRequest;
import dev.library.book.repository.BookRepository;
import dev.library.exception.BookNullPointerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookStateServiceImpl implements BookStateService{

    private final BookRepository bookRepository;

    public BookStateServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public BookStateEnum changeBookState(BookRequest bookRequest) {
        Optional<Book> selectedBook = bookRepository.findById(new BookId(bookRequest.getBookId()));
        if(selectedBook.isEmpty()) {
            throw new BookNullPointerException("해당 책을 찾을 수 없습니다");
        }
        Book book = selectedBook.get();
        book.convertStateToOpposite();

        return book.getBookState().getBookStateEnum();
    }


}
