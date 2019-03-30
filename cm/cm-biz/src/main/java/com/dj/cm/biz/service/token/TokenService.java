package com.dj.cm.biz.service.token;

import java.util.Map;

/**
 * Creates and validates credentials.
 */
public interface TokenService {

    String generatePermanentToken(Map<String, String> attributes);

    String generateExpiringToken(Map<String, String> attributes);

    /**
     * Checks the validity of the given credentials.
     *
     * @param token
     * @return attributes if verified
     */
    Map<String, String> verifyToken(String token);
}