package dev.library.admin.application;

import dev.library.book.dto.BookTitleDTO;
import dev.library.book.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateBookByAdminServiceImplTest {

    @Autowired
    private CreateBookByAdminService createBookByAdminService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("관리자 책 추가 테스트 - 정상")
    @Transactional
    void createBookTest() {
        //given
        BookTitleDTO bookTitleDTO = new BookTitleDTO("라일라이");
        bookRepository.deleteAll();
        //when
        String book = createBookByAdminService.createBook(bookTitleDTO);
        //then
        assertThat(book).isEqualTo("b-g-0");
    }
}