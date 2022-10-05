package dev.library.rental.repository;

import dev.library.rental.domain.Rental;
import dev.library.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findByUser(User user);

    @Query(value = "select * from rental where user_id = ? and return_date is null", nativeQuery = true)
    List<Rental> findByUserNoReturn(User user);

    @Query(value = "select * from rental where book_id = ? and return_date is null", nativeQuery = true)
    Rental findByBookId(String bookId);
}
