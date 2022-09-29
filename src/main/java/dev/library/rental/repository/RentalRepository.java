package dev.library.rental.repository;

import dev.library.rental.domain.Rental;
import dev.library.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findByUser(User user);
}
