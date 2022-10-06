package dev.library.user.presentation;

import dev.library.book.dto.BookDTO;
import dev.library.book.dto.BookIdDTO;
import dev.library.rental.application.RentBookService;
import dev.library.rental.application.ReturnLentBookService;
import dev.library.rental.dto.AfterReturnDTO;
import dev.library.rental.dto.BookIdsDTO;
import dev.library.rental.dto.RentalDTO;
import dev.library.rental.dto.UserAndBookIdDTO;
import dev.library.user.dto.UserDTO;
import dev.library.user.dto.UserIdDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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

//    @Test
//    public void RentalTest(){
//
//        UserAndBookIdDTO userAndBookIdDTO = new UserAndBookIdDTO();
//        userAndBookIdDTO.setUserId("user-g-0");
//        List<String> list = new ArrayList<>();
//        list.add("book-g-0");
//        list.add("book-g-1");
//        userAndBookIdDTO.setBookId(list);
//        System.out.println(userAndBookIdDTO.getBookId());
//
//        List<RentalDTO> result = new ArrayList<>();
//        if (userAndBookIdDTO!=null){
//            for(String bookId : userAndBookIdDTO.getBookId()){
//                System.out.println(bookId);
//                result.add(rentBookService.rentBook(userAndBookIdDTO.getUserId(),bookId));
//            }
//        }
//
//        System.out.println(result);
//
//    }

    @Test
    public void returnTest(){

        BookIdsDTO bookIdsDTO = new BookIdsDTO();
        List<String> list = new ArrayList<>();
        list.add("book-g-0");
        list.add("book-g-1");
        bookIdsDTO.setBookId(list);

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

        System.out.println(result);

    }

}
