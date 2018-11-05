package com.dj.cm.biz.service.user.impl;

import com.dj.cm.biz.service.exception.AlreadyExistBizException;
import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import com.dj.cm.persistence.repo.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

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
        Optional<UserModel> foundUser = userRepository.findById(id);
        if (!foundUser.isPresent()){
            throw new NotFoundBizException("User with id:" + id + " not found");
        }

        return foundUser.get();
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<UserModel> foundUser = userRepository.findById(id);
        if (!foundUser.isPresent()){
            throw new NotFoundBizException("User with id: " + id + " not found");
        }

        userRepository.deleteById(id);
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        if (userRepository.findUserModelByEmail(userModel.getEmail())!= null){
            throw new AlreadyExistBizException("User with Email: " + userModel.getEmail() + " already exist.");
        }

        return userRepository.save(userModel);
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        UserModel userToUpdate = getUserById(userModel.getId()); // Will throw NotFoundBizException if no user by id

        BeanUtils.copyProperties(userModel, userToUpdate, "id");
        return userRepository.save(userToUpdate);
    }
}
