package com.joljak.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

import com.joljak.auth.dao.AuthRepository;
import com.joljak.base.error.ErrorCode;
import com.joljak.base.exception.BusinessException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

import com.joljak.dto.AuthDto;
import com.joljak.base.constant.Constant;

@Slf4j
@Service("jwtService")
public class JwtServiceImpl implements JwtService {

    private static final String SALT = "lane4manager";

    @Value("${token.access}")
    private Integer accessExpiration;

    @Value("${token.refresh}")
    private Integer refreshExpiration;

    @Autowired
    AuthRepository authRepository;

    @Override
    public <T> String create(String userId, String authorityNm, String userNm, String compCd, String lastLoginDt) {
        String jwt = Jwts.builder().setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration)) //local,test : 30초(1000 * 30), prod : 1달(1000 * 60 * 60 * 24 *30)
                .claim("userId", userId)
                .claim("authorityNm", authorityNm)
                .claim("userNm", userNm)
                .claim("compCd", compCd)
                .claim("lastLoginDt", lastLoginDt)
                .signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
        return jwt;
    }

    @Override
    public <T> String createRefresh(String userId, String authorityNm) {
        String jwt = Jwts.builder().setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration)) //local,test : 1분(1000 * 60), prod : 1년(1000 * 60 * 60 * 24 * 30 * 12)
                .claim("userId", userId)
                .claim("authorityNm", authorityNm)
                .signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
        return jwt;
    }

    private byte[] generateKey() {
        byte[] key = DatatypeConverter.parseBase64Binary(JwtServiceImpl.SALT);
        return key;
    }

    @Override
    public boolean isUsable(String jwt, String requestUri) {
        String token = jwt.substring(7);
        AuthDto authDto = this.convertAuthDto(token);
        if (authDto == null) {
            return false;
        }
        String authorityNm = authDto.getAuthority();
        if (Constant.ROLE_ADMIN.equalsIgnoreCase(authorityNm)) {
            return true;
        } else if (Constant.ROLE_USER.equalsIgnoreCase(authorityNm)) {
            if (!token.equals(authDto.getToken())) {
                return false;
            }
            return true;
        } else if (Constant.ROLE_DRIVER.equalsIgnoreCase(authorityNm)) {
            if (!token.equals(authDto.getToken())) {
                return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public AuthDto getAuthDto() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader(Constant.HEADER_KEY);
        String token = jwt.substring(7);
        return this.convertAuthDto(token);
    }

    private AuthDto convertAuthDto(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
        } catch (ExpiredJwtException e) {
            throw new BusinessException(ErrorCode.EXPIRED_TOKEN_ERROR);
        } catch (Exception e) {
            return null;
        }

        Claims claim = claims.getBody();

        String userId = (String) claim.get("userId");
        String authorityNm = (String) claim.get("authorityNm");
        String userNm = (String) claim.get("userNm");
        String compCd = (String) claim.get("compCd");

        AuthDto authDto = new AuthDto();
        if (Constant.ROLE_DRIVER.equals(authorityNm)) {
            authDto.setDrvId(userId);
            authDto.setDrvNm(userNm);
            authDto.setToken(authRepository.getDrvToken(userId));
        } else if (Constant.ROLE_USER.equals(authorityNm)) {
            authDto.setUserId(userId);
            authDto.setUserNm(userNm);
            authDto.setToken(authRepository.getSvcUserToken(userId));
        } else if (Constant.ROLE_ADMIN.equals(authorityNm)) {
            authDto.setEmpId(userId);
            authDto.setEmpNm(userNm);
        }
        authDto.setCompCd(compCd);
        authDto.setAuthority(authorityNm);
        return authDto;
    }

    @Override
    public AuthDto getRefreshAuthDto() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader(Constant.HEADER_KEY);
        String refreshToken = jwt.substring(7);
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new BusinessException(ErrorCode.JWT_AUTH_ERROR);
        } catch (Exception e) {
            return null;
        }
        Claims claim = claims.getBody();

        String userId = (String) claim.get("userId");
        String authorityNm = (String) claim.get("authorityNm");
        AuthDto authDto = new AuthDto();
        if (Constant.ROLE_DRIVER.equals(authorityNm)) {
            String orgToken = authRepository.getDrvRefreshToken(userId);
            if (!orgToken.equals(refreshToken)) { //DB에 저장된 리프레시 토큰과 요청온 리프레시 토큰이 다른경우 에러처리해서 재로그인하도록
                throw new BusinessException(ErrorCode.JWT_AUTH_ERROR);
            }
            authDto.setDrvId(userId);
        } else if (Constant.ROLE_USER.equals(authorityNm)) {
            String orgToken = authRepository.getSvcUserRefreshToken(userId);
            if (!orgToken.equals(refreshToken)) {
                throw new BusinessException(ErrorCode.JWT_AUTH_ERROR);
            }
            authDto.setUserId(userId);
        } else if (Constant.ROLE_ADMIN.equals(authorityNm)) {
            authDto.setEmpId(userId);
        }
        authDto.setAuthority(authorityNm);
        return authDto;
    }

    public AuthDto checkExpiredPage() {
        Jws<Claims> claims = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader(Constant.HEADER_KEY);
        try {
            claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
        } catch (ExpiredJwtException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Claims claim = claims.getBody();

        String userId = (String) claim.get("userId");
        AuthDto authDto = new AuthDto();
        authDto.setUserId(userId);
        return authDto;
    }
}