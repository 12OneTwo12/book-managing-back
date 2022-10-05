package dev.library.user.presentation;

import dev.library.book.dto.BookIdDTO;
import dev.library.rental.application.RentBookService;
import dev.library.rental.application.ReturnLentBookService;
import dev.library.rental.dto.AfterReturnDTO;
import dev.library.rental.dto.RentalDTO;
import dev.library.user.dto.UserIdDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RentalAndReturnTest {

    @Autowired
    RentBookService rentBookService;

    @Autowired
    ReturnLentBookService returnLentBookService;

//    @Test
//    public void RentalTest(){
//
//        UserAndBookIdDTO userAndBookIdDTO = new UserAndBookIdDTO();
//        userAndBookIdDTO.setUserId("user-g-0");
//        userAndBookIdDTO.setBookId("book-g-0");
//
//        RentalDTO result = rentBookService.rentBook(userAndBookIdDTO);
//
//        System.out.println(result);
//
//    }

//    @Test
//    public void returnTest(){
//
//        BookIdDTO bookId = new BookIdDTO();
//        bookId.setBookId("book-g-0");
//
//        AfterReturnDTO result = returnLentBookService.returnLentBook(bookId.getBookId());
//
//        System.out.println(result);
//
//    }

}
