package com.dj.cm.security.config;

import com.dj.cm.biz.service.token.TokenService;
import com.dj.cm.biz.service.user.UserService;
import com.dj.cm.model.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof TokenAuthentication) {
            TokenAuthentication readyAuthentication = processAuthentication((TokenAuthentication) authentication);
            return readyAuthentication;
        } else {
            authentication.setAuthenticated(false);
            return authentication;
        }
    }

    private TokenAuthentication processAuthentication(TokenAuthentication authentication) throws AuthenticationServiceException {
        String token = authentication.getToken();

        Map<String, String> claims = tokenService.verifyToken(token);

        if (claims.get("TOKEN_EXPIRATION_DATE") == null) {
            throw new AuthenticationServiceException("Invalid token");
        }

        Date expiredDate = new Date(claims.get("TOKEN_EXPIRATION_DATE"));
        if (expiredDate.after(new Date())) {
            return buildTokenAuthentication(authentication, claims);
        } else {
            throw new AuthenticationServiceException("Token expired date error");
        }
    }

    private TokenAuthentication buildTokenAuthentication(TokenAuthentication authentication, Map<String, String> claims) {
        if (claims.get("email") == null) {
            throw new AuthenticationServiceException("User disabled");
        }

        UserModel user = userService.getUserByEmail(claims.get("email"))
                .orElseThrow(
                        () -> new AuthenticationServiceException("User disabled")
                );

        //if (user.isEnabled()) {
            //Collection roles = user.getRoles;
            Collection roles = null;
            TokenAuthentication fullAuthentication = new TokenAuthentication(authentication.getToken(), true, roles, user);
            return fullAuthentication;
        //}
    }
}
