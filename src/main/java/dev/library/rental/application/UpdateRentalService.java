package dev.library.rental.application;

import dev.library.rental.repository.RentalRepository;
import dev.library.user.domain.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateRentalService {

    @Autowired
    RentalRepository rentalRepository;

    public void update(Rental updateState) {

        Rental updateRental = updateState.rentBook();

        rentalRepository.save(updateRental);
    }
}
