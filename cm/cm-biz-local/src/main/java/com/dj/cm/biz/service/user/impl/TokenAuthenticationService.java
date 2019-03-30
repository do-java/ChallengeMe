package com.dj.cm.biz.service.user.impl;

import com.dj.cm.biz.service.token.TokenService;
import com.dj.cm.biz.service.user.UserAuthenticationService;
import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service
public class TokenAuthenticationService implements UserAuthenticationService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @Override
    public Optional<String> login(String email, String password) {
        return userService
                .getUserByEmail(email)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> tokenService.generateExpiringToken(Collections.singletonMap("email", email)));
    }

    @Override
    public Optional<UserModel> findByToken(String token) {
        return Optional
                .of(tokenService.verifyToken(token))
                .map(map -> map.get("email"))
                .flatMap(userService::getUserByEmail);
    }

    @Override
    public void logout(UserModel user) {
        // Nothing to do
    }
}