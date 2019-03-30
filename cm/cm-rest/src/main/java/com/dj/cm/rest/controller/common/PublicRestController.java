package com.dj.cm.rest.controller.common;

import com.dj.cm.biz.service.user.UserAuthenticationService;
import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Usage:
 * <p>
 * fetch( '/rest/public/user/register', { method: 'POST', headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, body: JSON.stringify({email: 'test11@test.com', password: 'smith'})}).then(result => result.text().then(console.log))
 * fetch( '/rest/public/user/login', { method: 'POST', headers: { 'Content-Type': 'application/json' }, body:  JSON.stringify({email: 'test@test.com', password: 'smith'})}).then(result => result.text().then(console.log))
 *
 */

@RestController
@RequestMapping("rest/public")
public class PublicRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationService authentication;

    @PostMapping("/user/register")
    public Map<String, Object> register(@RequestBody Map<String, String> json) {
        userService.createUser(
            UserModel
                    .builder()
                    .email(json.get("email"))
                    .password(json.get("password"))
                    .build()
        );

        return login(json);
    }

    @PostMapping("/user/login")
    public Map<String, Object> login(@RequestBody Map<String, String> json) {
        /*
        return authentication
                .login(json.get("email"), json.get("password"))
                .orElseThrow(() -> new RuntimeException("Invalid password!"));
                */
        String token;
        UserModel user;

        if (json.get("token") != null) {
            token = json.get("token");
            user = authentication
                    .findByToken(token)
                    .orElseThrow(() -> new RuntimeException("User wasn't found!"));
        } else {
            token = authentication
                    .login(json.get("email"), json.get("password"))
                    .orElseThrow(() -> new RuntimeException("Invalid password!"));

            user = userService.getUserByEmail(json.get("email")).orElse(null);
        }

        return new HashMap<String, Object>() {{
            put("token", token);
            put("user", user);
        }};
    }
}
