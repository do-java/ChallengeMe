package com.dj.cm.security.config;

import com.dj.cm.model.entity.UserModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TokenAuthentication implements Authentication {
    private String token;
    private boolean isAuthenticated;
    private Collection roles;
    private UserModel user;

    public TokenAuthentication(String token) {
        this.token = token;
    }

    public TokenAuthentication(String token, boolean isAuthenticated, Collection roles, UserModel user) {
        this.token = token;
        this.isAuthenticated = isAuthenticated;
        this.roles = roles;
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        isAuthenticated = b;
    }

    @Override
    public String getName() {
        return null;
    }

    public String getToken() {
        return token;
    }
}
