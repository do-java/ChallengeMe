package com.dj.cm.biz.service.user;

import com.dj.cm.model.entity.UserModel;

public interface UserService {

    Iterable<UserModel> findAllUsers();
    UserModel getUserById(Long id);

    void deleteUserById(Long id);

    UserModel saveUser(UserModel userModel);

}
