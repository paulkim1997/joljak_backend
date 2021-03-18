package com.joljak.auth.service;

import com.joljak.dto.AuthDto;

public interface JwtService {
    public <T> String create(String userId, String authorityNm, String userNm, String compCd, String loginDt);

    public <T> String createRefresh(String userId, String authorityNm);

    AuthDto getAuthDto();

    AuthDto getRefreshAuthDto();

    boolean isUsable(String jwt, String requestUri);

    //public <T> String createExpirePeriod(String userId);
    public AuthDto checkExpiredPage();
}