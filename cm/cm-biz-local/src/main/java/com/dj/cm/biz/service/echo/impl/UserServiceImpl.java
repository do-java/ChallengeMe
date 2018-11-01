package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import com.dj.cm.persistence.repo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }
}
