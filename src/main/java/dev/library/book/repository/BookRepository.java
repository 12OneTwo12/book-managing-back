package dev.library.book.repository;
import dev.library.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, BookId> {

    default BookId nextBookId(long numberOfRows) {
        return new BookId(String.format("b-g-%d", numberOfRows++));
    }

}
