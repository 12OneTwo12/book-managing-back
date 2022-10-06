package dev.library.Rental.domain;

import dev.library.book.domain.Book;
import dev.library.rental.dto.RentalDTO;
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
    public Rental(User user, Book book, RentalDate rentalDate, Integer rentalId) {
        this.rentalId = rentalId;
        this.user = user;
        this.book = book;
        this.rentalDate = rentalDate;
    }

    public static Rental afterRentBook(User user, Book book,RentalDate rentalDate) {

        Rental updateRental = Rental.builder().user(user).book(book).rentalDate(rentalDate).build();

        return updateRental;
    }

}