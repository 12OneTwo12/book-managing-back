package dev.library.admin.application;

import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;
import dev.library.book.dto.BookTitleDTO;
import dev.library.book.repository.BookRepository;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
public class CreateBookByAdminServiceImpl implements CreateBookByAdminService {

    @Autowired
    BookRepository bookRepository;

    @Transactional
    @Override
    public String createBook(BookTitleDTO bookTitleDTO) {

        long numberOfRows = bookRepository.count();
        BookId id = bookRepository.nextBookId(numberOfRows);
        Book newBook = Book.createBook(bookTitleDTO,id);

        Book result = bookRepository.save(newBook);

        String barcodeResult = result.getId().createBarcodeAndReturn();
        return barcodeResult;
    }

}
