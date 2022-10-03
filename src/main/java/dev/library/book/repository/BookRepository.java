package dev.library.book.repository;

import dev.library.user.domain.Book;
import dev.library.user.domain.BookId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, BookId> {

}
