package com.dj.cm.rest.controller.user;

import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<User> getAll(){
        return userService.findAllUsers();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public User save(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }
    @DeleteMapping
    public void deleteById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

}
