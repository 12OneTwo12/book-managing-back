package dev.library.book.application;

import dev.library.book.DTO.BookDTO;
import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;
import dev.library.book.domain.BookState;
import dev.library.book.domain.BookStateEnum;
import dev.library.book.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ReturnBookStateServiceImplTest {

    @Autowired
    private ReturnBookStateService returnBookStateService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("북상태 반환 테스트 - 정상")
    @Transactional
    void returnBookStateById() {
        //given
        long numberOfRows = 1;
        BookId bookId = bookRepository.nextBookId(numberOfRows);
        bookRepository.save(new Book(bookId, "lala", new BookState(BookStateEnum.ABLE)));
        //when
        BookDTO bookDTO = returnBookStateService.returnBookStateById(bookId.getId());
        //then
        assertThat(bookDTO.getBookId()).isEqualTo(bookId.getId());
    }
}