package com.joljak.auth.service;

import com.joljak.auth.dao.AuthRepository;
import com.joljak.admin.user.dao.UserRepository;
import com.joljak.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

// import lombok.extern.slf4j.Slf4j;
// @Slf4j
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    MessageSource messageSource;


    @Autowired
    AuthRepository authRepository;

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthDto empSignIn(String empId, String password) {
        AuthDto authDto = authRepository.empSignIn(empId);
        try {
            // String encodedPassword = bCryptPasswordEncoder.encode(password);
            // log.info("encodedPassword : " + encodedPassword);
            if (bCryptPasswordEncoder.matches(password, authDto.getPwd())) {
                return authDto;
                // } else {
                /*
                 * 무작위로 계정을 알아내려할 때 비밀번호가 틀리다는 것은 아이디가
                 * 존재한다는 것을 알려주는 것. 없음과 비밀번호 틀림을 구분하여 알려주는것 비권장
                 */
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


    @Override
    public EmpDto getEmpInfo(EmpDto dto) {
        EmpDto emp = authRepository.getEmpInfo(dto);
        return emp;
    }

    @Override
    public SvcUserDto getUserInfo(String userId, String onLineYn) {
        SvcUserDto user = authRepository.getUserInfo(userId);
        return user;
    }

    public String generateRandomNo(int length) {
        Random rand = new Random();
        String authNo = "";
        for (int i = 0; i < length; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            authNo += ran;
        }
        return authNo;
    }

}