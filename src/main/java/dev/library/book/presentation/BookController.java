package dev.library.book.presentation;

import dev.library.book.application.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("books")
@RestController
@CrossOrigin("http://localhost:3000")
public class BookController {


}
