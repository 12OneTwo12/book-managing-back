package dev.library.user.repository;

import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserId> {

    default UserId nextUserId(long numberOfRows) {
        return new UserId(String.format("u-g-%d", numberOfRows++));
    }
}
