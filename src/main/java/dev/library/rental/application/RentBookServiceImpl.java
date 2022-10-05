package dev.library.rental.application;

import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;
import dev.library.book.domain.BookState;
import dev.library.book.domain.BookStateEnum;
import dev.library.book.dto.BookIdDTO;
import dev.library.book.repository.BookRepository;
import dev.library.rental.domain.Rental;
import dev.library.rental.domain.RentalDate;
import dev.library.rental.dto.RentalDTO;
import dev.library.rental.dto.UserAndBookIdDTO;
import dev.library.rental.repository.RentalRepository;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.domain.UserState;
import dev.library.user.dto.UserIdDTO;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RentBookServiceImpl implements RentBookService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RentalRepository rentalRepository;

    @Override
    @Transactional
    public RentalDTO rentBook(UserAndBookIdDTO userAndBookIdDTO) {

        Optional<User> user = userRepository.findById(UserId.of(userAndBookIdDTO.getUserId()));
        Optional<Book> book = bookRepository.findById(BookId.of(userAndBookIdDTO.getBookId()));
        RentalDTO result;

        if (user.isEmpty()){
            return null;
        } else {
            if (book.isEmpty()){
                return null;
            } else {
                Rental rental = Rental.builder().user(user.get()).book(book.get()).rentalDate(new RentalDate(LocalDate.now())).build();
                Rental rentResult = rentalRepository.save(rental);
                result = RentalDTO.entityToDTO(rentResult);

                int newCurrentRentedBooks = user.get().getUserState().getCurrentRentedBooks()+1;

                Boolean rentable = user.get().getUserState().isRentable();
                if (newCurrentRentedBooks>1){ // 만약 빌린 책이 두개 이상이라면
                    rentable = false;
                }

                UserState newUserState = new UserState(newCurrentRentedBooks,user.get().getUserState().getRentFreeDate(),rentable);

                User updateUser = User.Request.toEntity(User.Request.builder().userId(user.get().getId()).name(user.get().getName()).courseName(user.get().getCourseName()).userState(newUserState).build());
                userRepository.save(updateUser);

                BookState newBookState = new BookState(BookStateEnum.UNABLE);
                Book newBook = Book.Request.toEntity(Book.Request.builder().id(book.get().getId()).title(book.get().getTitle()).bookState(newBookState).build());
                bookRepository.save(newBook);
            }
        }

        return result;
    }
}
