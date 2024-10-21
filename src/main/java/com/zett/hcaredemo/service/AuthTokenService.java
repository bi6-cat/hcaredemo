package com.zett.hcaredemo.service;

public interface AuthTokenService {
    String getSubjectFromAccessToken(String accessToken);

    boolean validateAccessToken(String accessToken, String userId);
}
