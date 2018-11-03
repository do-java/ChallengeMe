package com.dj.cm.biz.service.echo.impl;

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
        Iterable<UserModel> usersList = userRepository.findAll();

        //https://www.baeldung.com/java-iterable-size
        //Check if Iterable not equals 0
        if (StreamSupport.stream(usersList.spliterator(), false).count() == 0){
            throw new NotFoundBizException(" Users Not Found");
        }else
            System.out.println(StreamSupport.stream(usersList.spliterator(), false).count());
        return usersList;
    }

    @Override
    public UserModel getUserById(Long id) {
        Optional<UserModel> foundUser = userRepository.findById(id);
        if (!foundUser.isPresent()){
            throw new NotFoundBizException("User with id:" + id + " not found");
        }else

        return foundUser.get();
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
    public UserModel createUser(UserModel userModel) {
        if (userRepository.findUserModelByEmail(userModel.getEmail())!= null){
            throw new AlreadyExistBizException("User with Email: " + userModel.getEmail() + " already exist.");
        }else
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
