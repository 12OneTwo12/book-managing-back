package dev.library.book.repository;

import dev.library.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;

public interface BookRepository extends JpaRepository<Book, BookId> {

    default BookId nextBookId(long numberOfRows) {
        return new BookId(String.format("book-g-%d", numberOfRows++));
    }

}
