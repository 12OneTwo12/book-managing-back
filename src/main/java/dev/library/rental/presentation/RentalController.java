package dev.library.rental.presentation;

import dev.library.book.DTO.BookDTO;
import dev.library.book.application.AfterRentBookService;
import dev.library.book.application.ReturnBookStateService;
import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;
import dev.library.book.dto.BookIdDTO;
import dev.library.rental.application.AfterRentBookUpdateRentalService;
import dev.library.rental.domain.Rental;
import dev.library.rental.dto.RentalDTO;
import dev.library.user.application.AfterRentBookUserService;
import dev.library.user.dto.UserDTO;
import dev.library.user.dto.UserIdDTO;
import dev.library.user.application.ReturnUserService;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.domain.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("rental")
@RestController
@CrossOrigin("http://localhost:3000")
public class RentalController {

    @Autowired
    ReturnUserService returnUserService;
    @Autowired
    ReturnBookStateService returnBookStateService;

    @Autowired
    AfterRentBookUserService afterRentBookUserService;
    @Autowired
    AfterRentBookService afterRentBookService;
    @Autowired
    AfterRentBookUpdateRentalService afterRentBookUpdateRentalService;

    @PostMapping("/rentBook")
    public void rentBook(@RequestBody UserIdDTO userIdDTO,
                              @RequestBody BookIdDTO bookIdDTO) {

        // id로 usertable 가져와서 상태 update
        UserDTO userDTO = returnUserService.returnUserById(userIdDTO.getUserId());
        User updatedUser = afterRentBookUserService.updateUserStateAfterRent(userDTO);

        // id로 booktable 가져와서 상태 update
        BookDTO bookDTO = returnBookStateService.returnBookStateById(bookIdDTO.getBookId());
        Book updatedBook = afterRentBookService.updateBookStateAfterRent(bookDTO);

        // rentalTable update
        Rental updatedRental = afterRentBookUpdateRentalService.updateRental(updatedUser, updatedBook);
        afterRentBookUpdateRentalService.updateRentalDate(updatedRental);
    }
}