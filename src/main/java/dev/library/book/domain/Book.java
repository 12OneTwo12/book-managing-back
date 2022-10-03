package dev.library.book.domain;

import dev.library.book.dto.BookDTO;
import dev.library.book.dto.BookTitleDTO;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "books")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class Book {

    @EmbeddedId
    private BookId id;

    private String title;

    @Embedded
    private BookState bookState;

    @Builder
    public Book(BookId id, String title, BookState bookState) {
        this.id = id;
        this.title = title;
        this.bookState = bookState;
    }
    public static Book createBook(BookId bookId, String title) {
        return new Book(bookId, title, new BookState(BookStateEnum.ABLE));
    }

    public void convertStateToOpposite() {
        if (this.getBookState().getBookStateEnum() == BookStateEnum.ABLE) {
            this.getBookState().setBookStateEnum(BookStateEnum.UNABLE);
        } else {
            this.getBookState().setBookStateEnum(BookStateEnum.ABLE);
        }

    }

    @Builder
    private Book(BookId id,String title){
        this.title = title;
        this.id = id;
    }
    public static Book createBook(BookTitleDTO bookTitleDTO,BookId id){
        return Book.builder().title(bookTitleDTO.getTitle()).id(id).build();

    }

    public static Book afterRentBookUpdateBook(BookDTO bookDTO) {

        // bookId
        BookId bookId = new BookId(bookDTO.getBookId());
        // book 상태 update
        BookState newBookState = new BookState(bookDTO.getBookState());
        newBookState.setBookStateEnum(BookStateEnum.UNABLE);
        Book updateBook = Book.Request.toEntity(Book.Request.builder().id(bookId)
                .title(bookDTO.getTitle())
                .bookState(newBookState)
                .build());

        return updateBook;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Request {
        private BookId id;
        @NotBlank
        private String title;
        private BookState bookState;

        public static Book toEntity(Book.Request request) {
            return Book.builder()
                    .id(request.getId())
                    .title(request.getTitle())
                    .bookState(request.getBookState())
                    .build();
        }

    }

}
