package dev.library.user.domain;

import dev.library.dto.RentalDTO;
import dev.library.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rental")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    private Book book;

    @Embedded
    private RentalDate rentalDate;

    @Builder
    public Rental(User user, Book book, RentalDate rentalDate) {
        this.user = user;
        this.book = book;
        this.rentalDate = rentalDate;
    }

    public Rental rentBook() {

        // checking userState and bookState
        // the number of books that user rented < 2 && none of overdue && the book can be rented
        // book 상태 update
        BookState newBookState = new BookState(this.getBook().getBookState().ableOrNot = false);
        Book updateBook = Book.Request.toEntity(Book.Request.builder().id(this.getBook().getId())
                .title(this.getBook().getTitle())
                .bookState(newBookState)
                .build());

        // userState update
        User updateUser = User.Request.toEntity(User.Request.builder().userId(this.getUser().getId())
                .name(this.getUser().getName())
                .courseName(this.getUser().getCourseName())
                .userState(this.getUser().getUserState())
                .build());

        // rentalDate update
        RentalDate updateRentalDate = new RentalDate();
        updateRentalDate.setRentDate(LocalDate.now());
        updateRentalDate.setReturnDate(LocalDate.now().plusDays(7));

        // rental table update
        Rental updateStates = new Rental(updateUser, updateBook, updateRentalDate);

        return updateStates;

    }

    @Setter @Getter
    @ToString
    @Builder @AllArgsConstructor
    public static class Request {
        private User user;
        private Book book;
        private RentalDate rentalDate;

        public static Rental toEntity(Rental.Request request) {
            return Rental.builder().user(request.getUser())
                    .book(request.getBook())
                    .rentalDate(request.getRentalDate())
                    .build();
        }

    }

}
