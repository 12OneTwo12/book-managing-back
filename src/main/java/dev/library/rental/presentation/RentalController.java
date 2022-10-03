package dev.library.rental.presentation;

import dev.library.book.application.UpdateBookService;
import dev.library.book.repository.BookRepository;
import dev.library.dto.BookIdDTO;
import dev.library.dto.RentalDTO;
import dev.library.dto.UserDTO;
import dev.library.dto.UserIdDTO;
import dev.library.rental.application.UpdateRentalService;
import dev.library.user.application.ReturnUserService;
import dev.library.user.application.UpdateUserService;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.domain.UserState;
import dev.library.user.repository.UserRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("rental")
@RestController
@CrossOrigin("http://localhost:3000")
public class RentalController {

    @Autowired
    ReturnUserService returnUserService;

    @Autowired
    UpdateUserService updateUserService;

    @Autowired
    UpdateBookService updateBookService;

    @Autowired
    UpdateRentalService updateRentalService;

    public RentalDTO rentBook(@RequestBody UserIdDTO userIdDTO,
                              @RequestBody BookIdDTO bookIdDTO) {

        UserDTO currentUserState = returnUserService.returnUserById(userIdDTO.getUserId());
        UserId userId = new UserId(currentUserState.getId());
        UserState newUserState = new UserState(currentUserState.getCurrentRentedBooks(), currentUserState.getRentFreeDate(),
                currentUserState.getRentable());
        updateUserService.update(User.Request.toEntity(User.Request.builder().userId(userId)
                .name(currentUserState.getName())
                .courseName(currentUserState.getCourseName())
                .userState(newUserState)
                .build()));

        // id로 찾아온 currentBookState값을 parameter로 넣을 것.
        updateBookService.update();

        // update 후 user와 book 넣어서 rental 만들어주고 parameter로 넣을 것.
        updateRentalService.update();

        return null;
    }
}
