package com.zett.hcaredemo.service.impl;

import com.zett.hcaredemo.constant.CacheConstant;
import com.zett.hcaredemo.service.AuthTokenService;
import com.zett.hcaredemo.service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;


@Slf4j
@Service
public class AuthTokenServiceImpl implements AuthTokenService {

    private final String accessTokenJwtSecret;
    private final String refreshTokenJwtSecret;
    private final RedisService redisService;

    public AuthTokenServiceImpl(
            @Value("${jwtSecret}") String accessTokenJwtSecret,
            @Value("${jwtSecret}") String refreshTokenJwtSecret, RedisService redisService) {
        this.accessTokenJwtSecret = accessTokenJwtSecret;
        this.refreshTokenJwtSecret = refreshTokenJwtSecret;
        this.redisService = redisService;
    }

    @Override
    @Transactional
    public String getSubjectFromAccessToken(String accessToken) {
        log.info("(getSubjectFromAccessToken)accessToken: {}", accessToken);
        return getClaim(accessToken, Claims::getSubject, accessTokenJwtSecret);
    }

    @Override
    @Transactional
    public boolean validateAccessToken(String accessToken, String username) {
        log.debug("(validateAccessToken)accessToken: {}, userId: {}", accessToken, username);
        String accessTokenHashkey = CacheConstant.ACCESS_TOKEN_HASH_KEY;
        if (redisService.findToken(username, accessTokenHashkey) == null ||
                !getSubjectFromAccessToken(accessToken).equals(username)) {
            return false;
        }
        return true;
    }
    private Claims getClaims(String token, String secretKey) {
        log.info("(getClaims)token: {} secretKey: {}", token, secretKey);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolve, String secretKey) {
        log.info("(getClaim) token: {}, claimResolve: {}, secretKey: {}", token, claimsResolve, secretKey);
        return claimsResolve.apply(getClaims(token, secretKey));
    }
}