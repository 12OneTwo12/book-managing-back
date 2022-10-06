package dev.library.rental.presentation;

import dev.library.book.dto.BookDTO;
import dev.library.book.application.AfterRentBookService;
import dev.library.book.application.ReturnBookStateService;
import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;
import dev.library.book.dto.BookIdDTO;
import dev.library.rental.application.AfterRentBookUpdateRentalService;
import dev.library.rental.application.RentBookService;
import dev.library.rental.application.ReturnLentBookService;
import dev.library.rental.domain.Rental;
import dev.library.rental.dto.AfterReturnDTO;
import dev.library.rental.dto.BookIdsDTO;
import dev.library.rental.dto.RentalDTO;
import dev.library.rental.dto.UserAndBookIdDTO;
import dev.library.user.application.AfterRentBookUserService;
import dev.library.user.dto.UserDTO;
import dev.library.user.dto.UserIdDTO;
import dev.library.user.application.ReturnUserService;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.domain.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("rental")
@RestController
@CrossOrigin("http://localhost:3000")
public class RentalController {

//    @Autowired
//    ReturnUserService returnUserService;
//    @Autowired
//    ReturnBookStateService returnBookStateService;
//
//    @Autowired
//    AfterRentBookUserService afterRentBookUserService;
//    @Autowired
//    AfterRentBookService afterRentBookService;
//    @Autowired
//    AfterRentBookUpdateRentalService afterRentBookUpdateRentalService;

    @Autowired
    ReturnLentBookService returnLentBookService;

    @Autowired
    RentBookService rentBookService;

//    @PostMapping("/rentBook")
//    public void rentBook(@RequestBody UserIdDTO userIdDTO,
//                              @RequestBody BookIdDTO bookIdDTO) {
//
//        // id로 usertable 가져와서 상태 update
//        UserDTO userDTO = returnUserService.returnUserById(userIdDTO.getUserId());
//        User updatedUser = afterRentBookUserService.updateUserStateAfterRent(userDTO);
//
//        // id로 booktable 가져와서 상태 update
//        BookDTO bookDTO = returnBookStateService.returnBookStateById(bookIdDTO.getBookId());
//        Book updatedBook = afterRentBookService.updateBookStateAfterRent(bookDTO);
//
//        // rentalTable update
//        Rental updatedRental = afterRentBookUpdateRentalService.updateRental(updatedUser, updatedBook);
//    }

    @PostMapping("/rentBook")
    public List<RentalDTO> rentBook(@RequestBody UserAndBookIdDTO userAndBookIdDTO) {
        // rentalTable update

        List<RentalDTO> result = new ArrayList<>();
        if (userAndBookIdDTO!=null){
            for(String bookId : userAndBookIdDTO.getBookId()){
                result.add(rentBookService.rentBook(userAndBookIdDTO.getUserId(),bookId));
            }
        } else {
            return null;
        }
        return result;
    }

    @PostMapping("/returnBook")
    public AfterReturnDTO returnBook(@RequestBody BookIdsDTO bookIdsDTO) {
        AfterReturnDTO result = null;
        List<BookDTO> bookList = new ArrayList<>();
        List<RentalDTO> rentalList = new ArrayList<>();

        for(String bookId : bookIdsDTO.getBookId()){
            result = returnLentBookService.returnLentBook(bookId);
            if(result != null){
                bookList.add(result.getBookDTO().get(0));
                rentalList.add(result.getRentalDTO().get(0));
            }
        }

        result.setBookDTO(bookList);
        result.setRentalDTO(rentalList);

        return result;
    }
}
