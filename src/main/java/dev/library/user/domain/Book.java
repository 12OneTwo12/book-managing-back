package dev.library.user.domain;

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

    public Book afterRentBookUpdateBook() {

        // book 상태 update
        BookState newBookState = new BookState(this.getBookState().ableOrNot = false);
        Book updateBook = Book.Request.toEntity(Book.Request.builder().id(this.getId())
                .title(this.getTitle())
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
