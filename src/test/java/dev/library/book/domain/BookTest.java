package dev.library.book.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookTest {

    @Test
    @DisplayName("북엔티티 생성 테스트 - 정상")
    void createBookTest(){
        //given
        BookId bookId = new BookId("id");
        String title = "testTitle";
        BookState bookState = new BookState(BookStateEnum.ABLE);
        //when
        Book book = new Book(bookId, title, bookState);
        //then
        assertThat(book.getId()).isEqualTo(bookId);
    }

    @Test
    @DisplayName("북 상태 변환 테스트 - 정상")
    void convertStateToOppositeTest(){
        //given
        BookId bookId = new BookId("id");
        String title = "testTitle";
        BookState bookState = new BookState(BookStateEnum.ABLE);
        Book book = new Book(bookId, title, bookState);
        //when
        book.convertStateToOpposite();
        //then
        assertThat(book.getBookState().getBookStateEnum()).isEqualTo(BookStateEnum.UNABLE);
    }

}