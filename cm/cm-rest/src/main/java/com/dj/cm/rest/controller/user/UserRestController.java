package com.dj.cm.rest.controller.user;

import com.dj.cm.biz.service.user.UserAuthenticationService;
import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Usage:
 * <p>
 * GET http://localhost:8080/rest/user/
 * GET http://localhost:8080/rest/user/1
 * fetch('/rest/user', { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({userName:'Test User', email:'test@test.com', password:'1234', confirmed: true, deleted: false, id: null}) }).then(result => result.json().then(console.log))
 * fetch('/rest/user/1', { method: 'PUT', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({ userName:'Test User1', email:'test@test.com', password:'1234', confirmed: true, deleted: false, id: 1}) }).then(result => result.json().then(console.log))
 * fetch('/rest/user/1', { method: 'DELETE' }).then(result => console.log(result))
 * fetch( '/rest/user/register', { method: 'POST', headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, body: 'username=john&email=test11@test.com&password=smith'}).then(result => result.text().then(console.log))
 * fetch( '/rest/user/login', { method: 'POST', headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, body: 'email=test@test.com&password=smith'}).then(result => result.text().then(console.log))
 *
 */

@RestController
@RequestMapping("rest/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationService authentication;

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

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email
    ) {
        userService.createUser(
                        UserModel
                                .builder()
                                .userName(username)
                                .password(password)
                                .email(email)
                                .build()
                );

        return login(email, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return authentication
                .login(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid login and/or password"));
    }

    @GetMapping("/logout")
    boolean logout(@AuthenticationPrincipal final UserModel user) {
        authentication.logout(user);
        return true;
    }

}
