package com.dj.cm.rest.controller.user;

import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Usage:
 * <p>
 * GET http://localhost:8080/rest/user/
 * GET http://localhost:8080/rest/user/1
 * fetch('/rest/user', { method: 'POST', headers: { 'Content-Type': 'application/json' },body: JSON.stringify({userName:'Test User', email:'test@test.com', password:'1234', confirmed: true, deleted: false, id: null}) }).then(result => result.json().then(console.log))
 * fetch('/rest/user/1', { method: 'PUT', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({ userName:'Test User1', email:'test@test.com', password:'1234', confirmed: true, deleted: false, id: 1}) }).then(result => result.json().then(console.log))
 * fetch('/rest/user/1', { method: 'DELETE' }).then(result => console.log(result))
 *
 */

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
        return userService.getUserById(id);
    }

    @PostMapping
    public UserModel create(@RequestBody UserModel userModel){

        return userService.createUser(userModel);
    }

    @PutMapping("{id}")
    public UserModel update(@PathVariable Long id, @RequestBody UserModel user){
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

}
