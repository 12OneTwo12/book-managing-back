package dev.library.book.repository;

import dev.library.book.domain.BookId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("북레포지토리 nextBookId 테스트 - 정상")
    void createBookIdTest(){
        //given
        long numberOfRows = 1;
        //when
        BookId bookId = bookRepository.nextBookId(numberOfRows);
        //then
        assertThat(bookId.getId()).isEqualTo("b-g-1");
    }


}