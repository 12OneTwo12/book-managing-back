package dev.library.book.dto;

import dev.library.book.domain.Book;
import dev.library.book.domain.BookStateEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BookDTO {

    private String bookId;
    private String title;
    private BookStateEnum bookState;

    public static BookDTO entityToDTO(Book book){
        return BookDTO.builder().bookId(book.getId().getId())
                                .title(book.getTitle())
                                .bookState(book.getBookState().getBookStateEnum())
                                .build();
    }




}
