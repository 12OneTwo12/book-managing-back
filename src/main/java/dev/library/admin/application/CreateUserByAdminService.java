package dev.library.admin.application;

import dev.library.user.domain.User;
import dev.library.user.domain.UserId;

public interface CreateUserByAdminService {

    public String saveUser(User.Request request);

}
