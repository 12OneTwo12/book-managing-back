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
        // Book id로 반납되지 않은 대여 기록 조회
        if (rental == null){ // null 체크
            return null;
        } else {
            Book book = rental.getBook();
            BookState newBookState = new BookState(BookStateEnum.ABLE);
            // Book 대여가능 상태 Able로 변경
            Book newBook = Book.Request.toEntity(Book.Request.builder().id(book.getId()).title(book.getTitle()).bookState(newBookState).build());

            Book bookResult = bookRepository.save(newBook); //ABLE 상태로 Book 저장

            LocalDate rentDate = rental.getRentalDate().getRentDate(); // 빌린 날짜 값
            LocalDate daedlineDate = rentDate.plusDays(7); // 반납 기한 설정

            Rental updateRental = Rental.builder().rentalId(rental.getRentalId()).user(rental.getUser()).book(rental.getBook()).rentalDate(new RentalDate(rental.getRentalDate().getRentDate(), LocalDate.now())).build();
            Rental rentalResult = rentalRepository.save(updateRental);
            // Rental Update

            User user = rental.getUser();
            int newCurrentRentedBooks = user.getUserState().getCurrentRentedBooks()-1;
            // 반납 후 빌린 책 갯수
            
            Boolean userRentable = user.getUserState().isRentable(); // 새로운 유저 대여 가능 상태

            UserState newUserState; // 새로운 유저 상태
            if (daedlineDate.isBefore(LocalDate.now())){ // 만약 반납 기한이 지났으면
                userRentable = false;
                Period period = Period.between(daedlineDate, LocalDate.now()); // 지난 만큼 값을
                LocalDate freeDate = LocalDate.now().plus(period); // 오늘 날짜로부터 더해서
                newUserState = new UserState(newCurrentRentedBooks,freeDate,userRentable); // freedate로 설정
            } else if(user.getUserState().getRentFreeDate() == null && newCurrentRentedBooks < 2) { // 만약 freedate도 null이고 빌린 책도 2개 이하라면 rentable을 true로
                newUserState = new UserState(newCurrentRentedBooks, user.getUserState().getRentFreeDate(), true);
            } else if(LocalDate.now().isBefore(user.getUserState().getRentFreeDate())) { // 만약 현재가 renfreedate보다 시간적으로 전이라면 아직 패널티 중이여야 함으로 기존 상태 그대로
                newUserState = new UserState(newCurrentRentedBooks,user.getUserState().getRentFreeDate(), userRentable);
            } else {
                userRentable = true;
                newUserState = new UserState(newCurrentRentedBooks,user.getUserState().getRentFreeDate(), userRentable);
                // 위 제약사항이 모두 거짓이라면 userRentable을 가능으로 변경
            }
            User newUser = User.Request.toEntity(User.Request.builder().userId(user.getId()).name(user.getName()).courseName(user.getCourseName()).userState(newUserState).build());
            User userResult = userRepository.save(newUser);
            // User Update

            AfterReturnDTO result = AfterReturnDTO.entityToDTO(bookResult,rentalResult,userResult);
            // 각 엔티티 한 DTO로 바꿔주고 결과값 Return

            return result;
        }

    }
}
