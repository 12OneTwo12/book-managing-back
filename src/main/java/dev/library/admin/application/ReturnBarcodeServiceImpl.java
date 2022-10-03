package dev.library.admin.application;

import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;
import dev.library.book.repository.BookRepository;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturnBarcodeServiceImpl implements ReturnBarcodeService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public String returnUserBarcode(String id) {

        Optional<User> findUser = userRepository.findById(UserId.of(id));

        if(findUser.isEmpty()){
            return null;
        } else {
            return findUser.get().getId().returnBarcode();
        }
    }

    @Override
    public String returnBookBarcode(String id) {

        Optional<Book> findBook = bookRepository.findById(BookId.of(id));

        if(findBook.isEmpty()){
            return null;
        } else {
            return findBook.get().getId().returnBarcode();
        }

    }

}
