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
    public RentalDTO rentBook(String userId, String bookId) {

        Optional<User> user = userRepository.findById(UserId.of(userId));
        Optional<Book> book = bookRepository.findById(BookId.of(bookId));
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

                int newCurrentRentedBooks = user.get().getUserState().getCurrentRentedBooks()+1; // 기존 대여 갯수에 지금 대여가 일어나는 책 값 하나를 더해줌

                Boolean rentable = user.get().getUserState().isRentable(); // 기존 유저 책 대여가능 상태를 기본값으로
                if (newCurrentRentedBooks>1){ // 만약 빌린 책이 두개 이상이라면
                    rentable = false; // 대여 불가로 변경
                }

                UserState newUserState = new UserState(newCurrentRentedBooks,user.get().getUserState().getRentFreeDate(),rentable);
                // 새로운 유저 상태 세팅

                User updateUser = User.Request.toEntity(User.Request.builder().userId(user.get().getId()).name(user.get().getName()).courseName(user.get().getCourseName()).userState(newUserState).build());
                userRepository.save(updateUser);
                // User Update

                BookState newBookState = new BookState(BookStateEnum.UNABLE);
                // 책 상태 세팅
                Book newBook = Book.Request.toEntity(Book.Request.builder().id(book.get().getId()).title(book.get().getTitle()).bookState(newBookState).build());
                bookRepository.save(newBook);
                // Book Update
            }
        }

        return result;
    }
}
