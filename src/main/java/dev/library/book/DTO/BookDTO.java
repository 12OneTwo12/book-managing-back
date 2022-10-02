package dev.library.book.DTO;

import dev.library.book.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BookDTO {

    private String bookId;
    private String title;
    private Enum bookState;

    public static BookDTO entityToDTO(Book book){
        return BookDTO.builder().bookId(book.getId().getId())
                                .title(book.getTitle())
                                .bookState(book.getBookState().getBookStateEnum())
                                .build();
    }




}
