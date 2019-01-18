package com.dj.cm.rest.controller.user;

import com.dj.cm.biz.service.exception.AlreadyExistBizExeption;
import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<UserModel> getAll(){
        return userService.findAllUsers();
    }

    @GetMapping("{id}")
    public UserModel getById(@PathVariable Long id){
        UserModel foundUser = userService.getUserById(id);
        if (foundUser == null){
            throw new NotFoundBizException("User with id =" + id + " was not found");
        }else
            return foundUser;
    }

    @PostMapping
    public UserModel save(@RequestBody UserModel userModel){
        if (userModel.getId().equals(userService.getUserById(userModel.getId()).getId())) {
            throw new AlreadyExistBizExeption("User with id =" + userModel.getId() + " already exist");
        }else
            userService.saveUser(userModel);

        return userModel;
    }
    @DeleteMapping
    public void deleteById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

}
