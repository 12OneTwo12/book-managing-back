package dev.library.book.repository;

<<<<<<< HEAD
import dev.library.user.domain.UserId;
=======
>>>>>>> 76449972cbdcb5dd1ea94813a3052be3b075fecd
import org.springframework.data.jpa.repository.JpaRepository;
import dev.library.book.domain.Book;
import dev.library.book.domain.BookId;

public interface BookRepository extends JpaRepository<Book, BookId> {

    default BookId nextBookId(long numberOfRows) {
        return new BookId(String.format("b-g-%d", numberOfRows++));
    }

}
