package dev.library.rental.application;

import dev.library.book.domain.Book;
import dev.library.rental.domain.Rental;
import dev.library.user.domain.User;

public interface AfterRentBookUpdateRentalService {

    public Rental updateRental(User user, Book book);

    public void updateRentalDate(Rental updatedRental);
}
