package com.joljak.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joljak.auth.service.JwtService;
import com.joljak.base.constant.Constant;

import com.joljak.base.error.ErrorCode;
import com.joljak.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(Constant.HEADER_KEY);
        if ("OPTIONS".equals(request.getMethod())) { // HTTP Method 가 OPTION 일 경우에는 무조건 true, 참true
            // log.debug("token : " + token + "\tURI : " + request.getRequestURI() + "\tMethod : " + request.getMethod());
            return true;
        }
        if (token == null) {
            response.setStatus(401);
            throw new BusinessException(ErrorCode.EXPIRED_TOKEN_ERROR);
        } else if (token != null && jwtService.isUsable(token, request.getRequestURI())) {
            return true;
        } else {
            response.setStatus(403);
            throw new BusinessException(ErrorCode.JWT_AUTH_ERROR);
        }
    }
}