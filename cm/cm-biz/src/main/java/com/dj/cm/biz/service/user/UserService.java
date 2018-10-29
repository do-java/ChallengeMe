package com.dj.cm.biz.service.user;

import com.dj.cm.model.entity.User;

public interface UserService {

    Iterable<User> findAllUsers();
    User getUserById(Long id);

    void deleteUserById(Long id);

    User saveUser(User user);

}
