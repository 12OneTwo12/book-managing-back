package dev.library.rental.application;

import dev.library.book.domain.Book;
import dev.library.book.domain.BookState;
import dev.library.book.domain.BookStateEnum;
import dev.library.book.repository.BookRepository;
import dev.library.rental.domain.Rental;
import dev.library.rental.domain.RentalDate;
import dev.library.rental.dto.AfterReturnDTO;
import dev.library.rental.repository.RentalRepository;
import dev.library.user.domain.User;
import dev.library.user.domain.UserState;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service
public class ReturnLentBookServiceImpl implements ReturnLentBookService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RentalRepository rentalRepository;

    @Override
    @Transactional
    public AfterReturnDTO returnLentBook(String bookId) {

        Rental rental = rentalRepository.findByBookId(bookId);
        if (rental == null){
            return null;
        } else {
            Book book = rental.getBook();
            BookState newBookState = new BookState(BookStateEnum.ABLE);
            Book newBook = Book.Request.toEntity(Book.Request.builder().id(book.getId()).title(book.getTitle()).bookState(newBookState).build());

            Book bookResult = bookRepository.save(newBook); //ABLE 상태로 Book 저장

            LocalDate rentDate = rental.getRentalDate().getRentDate();
            LocalDate daedlineDate = rentDate.plusDays(7);
            Rental updateRental = Rental.builder().rentalId(rental.getRentalId()).user(rental.getUser()).book(rental.getBook()).rentalDate(new RentalDate(rental.getRentalDate().getRentDate(), LocalDate.now())).build();
            Rental rentalResult = rentalRepository.save(updateRental);

            User user = rental.getUser();
            int newCurrentRentedBooks = user.getUserState().getCurrentRentedBooks()-1;
            Boolean userRentable;
            UserState newUserState;
            if (daedlineDate.isBefore(LocalDate.now())){
                userRentable = false;
                Period period = Period.between(daedlineDate, LocalDate.now());
                LocalDate freeDate = LocalDate.now().plus(period);
                newUserState = new UserState(newCurrentRentedBooks,freeDate,userRentable);
            } else if(user.getUserState().getRentFreeDate() == null && newCurrentRentedBooks < 2) {
                newUserState = new UserState(newCurrentRentedBooks, user.getUserState().getRentFreeDate(), true);
            } else if(LocalDate.now().isBefore(user.getUserState().getRentFreeDate())) {
                newUserState = new UserState(newCurrentRentedBooks,user.getUserState().getRentFreeDate(), user.getUserState().isRentable());
            } else {
                userRentable = true;
                newUserState = new UserState(newCurrentRentedBooks,user.getUserState().getRentFreeDate(), userRentable);
            }
            User newUser = User.Request.toEntity(User.Request.builder().userId(user.getId()).name(user.getName()).courseName(user.getCourseName()).userState(newUserState).build());
            User userResult = userRepository.save(newUser);

            AfterReturnDTO result = AfterReturnDTO.entityToDTO(bookResult,rentalResult,userResult);

            return result;
        }

    }
}
