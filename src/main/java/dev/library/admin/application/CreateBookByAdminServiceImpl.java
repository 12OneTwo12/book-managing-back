package dev.library.admin.application;

import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;
import dev.library.book.dto.BookTitleDTO;
import dev.library.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateBookByAdminServiceImpl implements CreateBookByAdminService {

    @Autowired
    BookRepository bookRepository;

    @Transactional
    @Override
    public String createBook(BookTitleDTO bookTitleDTO) {

        long numberOfRows = bookRepository.count();
        BookId id = bookRepository.nextBookId(numberOfRows);

        Book newBook = Book.createBook(id, bookTitleDTO.getTitle());

        Book result = bookRepository.save(newBook);

        return result.getId().getId();
//                .createBarcodeAndReturn();
    }

}
