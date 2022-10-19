package dev.library.rental.application;

import dev.library.book.domain.Book;
import dev.library.rental.domain.RentalDate;
import dev.library.rental.repository.RentalRepository;
import dev.library.rental.domain.Rental;
import dev.library.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AfterRentBookUpdateRentalServiceImpl implements AfterRentBookUpdateRentalService{

    @Autowired
    RentalRepository rentalRepository;

    public Rental updateRental(User user, Book book) {

        Rental updateRental = Rental.afterRentBook(user, book, new RentalDate(LocalDate.now()));

        return rentalRepository.save(updateRental);
    }

    @Override
    public void updateRentalDate(Rental updatedRental) {

        // returndate 다시 설정해주기
        Rental updateRental = Rental.afterRentBook(updatedRental.getUser(), updatedRental.getBook(), new RentalDate(LocalDate.now()));
        // returnDate 수정한 rental table update
        rentalRepository.save(updateRental);

    }

}
