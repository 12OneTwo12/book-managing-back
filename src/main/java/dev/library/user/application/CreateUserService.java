package dev.library.user.application;

import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserId saveUser(User.Request request) {

        // 다음 ID number 생성 ex) 현재 User DB에 userId가 u-g-1까지 있을 경우 새로운 id는 'u-g-2'
        long numberOfRows = userRepository.count();
        UserId id = userRepository.nextUserId(numberOfRows);
        request.setUserId(id);

        User user = User.Request.toEntity(request);
        userRepository.save(user);
        return id;
    }
}
