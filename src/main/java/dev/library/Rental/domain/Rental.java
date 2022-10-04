package dev.library.rental.domain;

import dev.library.book.domain.Book;
import dev.library.user.domain.User;
import lombok.*;

import javax.persistence.*;

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
    @JoinColumn(name = "user_id",referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    private Book book;

    @Embedded
    private RentalDate rentalDate;

    @Builder
    public Rental(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public Rental(User user, Book book, RentalDate rentalDate) {
        this.user = user;
        this.book = book;
        this.rentalDate = rentalDate;
    }

    public static Rental afterRentBook(User user, Book book) {

        Rental updateRental = Rental.builder().user(user).book(book).build();

        return updateRental;
    }

}