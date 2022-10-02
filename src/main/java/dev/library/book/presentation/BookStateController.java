package dev.library.book.presentation;

import dev.library.book.DTO.BookDTO;
import dev.library.book.application.BookStateService;
import dev.library.book.application.ReturnBookStateService;
import dev.library.book.domain.BookStateEnum;
import dev.library.book.presentation.request.BookRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("books")
@RestController
@CrossOrigin("http://localhost:3000")
public class BookStateController {

    private final BookStateService bookStateService;
    private final ReturnBookStateService returnBookStateService;

    public BookStateController(BookStateService bookStateService, ReturnBookStateService returnBookState) {
        this.bookStateService = bookStateService;
        this.returnBookStateService = returnBookState;
    }

    @PutMapping("/bookstate/opposite")
    public ResponseEntity<BookStateEnum> bookStateOppositeModify(@RequestBody BookRequest bookRequest){
        return ResponseEntity.ok().body(bookStateService.changeBookState(bookRequest));
    }

    @GetMapping("/bookstate/{id}")
    public ResponseEntity<BookDTO> bookDTOResponseEntity(@PathVariable("id") String bookId){
        return ResponseEntity.ok().body(returnBookStateService.returnBookStateById(bookId));
    }

}