package com.dj.cm.rest.controller.user;

import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<UserModel> getAll(){
        return userService.findAllUsers();
    }

    @GetMapping("{id}")
    public UserModel getById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public UserModel save(@RequestBody UserModel userModel){
        userService.saveUser(userModel);
        return userModel;
    }
    @DeleteMapping
    public void deleteById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

}
