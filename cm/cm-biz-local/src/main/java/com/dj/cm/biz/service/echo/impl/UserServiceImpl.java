package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.exception.AlreadyExistBizExeption;
import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import com.dj.cm.persistence.repo.user.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<UserModel> foundUser = userRepository.findById(id);
        if (!foundUser.isPresent()){
            throw new NotFoundBizException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        UserModel userToUpdate = getUserById(userModel.getId());
        if (userToUpdate == null){
            throw new NotFoundBizException("User Not Found");
        }else
            BeanUtils.copyProperties(userModel, userToUpdate, "id");
        return userRepository.save(userToUpdate);
    }
}
