package com.dj.cm.biz.service.user.impl;

import com.dj.cm.biz.service.token.TokenService;
import com.dj.cm.biz.service.user.UserAuthenticationService;
import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TokenAuthenticationService implements UserAuthenticationService {
    @Autowired
    private TokenService tokens;
    @Autowired
    private UserService users;

    @Override
    public Optional<String> login(String email, String password) {
        System.out.println(email + "   " + password);
        return users
                .getUserByEmail(email)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> tokens.expiring(ImmutableMap.of("email", email)));
    }

    @Override
    public Optional<UserModel> findByToken(String token) {
        return Optional
                .of(tokens.verify(token))
                .map(map -> map.get("email"))
                .flatMap(users::getUserByEmail);
    }

    @Override
    public void logout(UserModel user) {
        // Nothing to doy
    }
}