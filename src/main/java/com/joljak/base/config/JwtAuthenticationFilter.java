package com.joljak.base.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.joljak.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {
    @Autowired
    AuthService authService;

    // Jwt Provier 주입
    public JwtAuthenticationFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        //헤더에서 토큰 가지고 오는 부분
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        //if ( !ObjUtils.isEmpty(token) ) {
        //토큰에 있는 회원정보 가져옴
        //AuthVo authVo = authService.getAuthentication(token);

        //if("true".equals(authVo.getResult())) {
        if (1 == 1) {
            SecurityContext sc = SecurityContextHolder.getContext();
            //아이디, 패스워드, 권한을 설정합니다. 아이디는 Object단위로 넣어도 무방하며
            //패스워드는 null로 하여도 값이 생성됩니다.
            //sc.setAuthentication(new UsernamePasswordAuthenticationToken(authVo.getMmbrNmbr(), authVo.getMmbrNm(), null));
            sc.setAuthentication(new UsernamePasswordAuthenticationToken("123", "456", null));
        }
        //}
        chain.doFilter(request, response);
    }
}
