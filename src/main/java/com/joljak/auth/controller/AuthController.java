package com.joljak.auth.controller;

import com.joljak.admin.user.dao.UserRepository;
import com.joljak.admin.user.service.UserService;
import com.joljak.base.dto.ResponseApiDto;
import com.joljak.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.joljak.auth.service.AuthService;
import com.joljak.auth.service.JwtService;
import com.joljak.base.constant.Constant;
import com.joljak.base.dto.ResponseDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Resource(name = "authService")
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * 사용자 로그인하기
     *
     * @param    {empId, pwd}
     * @return ResponseDto (결과 형식 설계 필요)
     */
    @RequestMapping(value = "/empSignIn", method = RequestMethod.POST)
    public ResponseDto empSignIn(@RequestBody EmpDto reqEmpDto, HttpServletResponse response) {
        logger.debug("empSignIn");
        String empId = reqEmpDto.getEmpId();
        String password = reqEmpDto.getPwd();

        ResponseDto responseDto = new ResponseDto();
        if (empId == null || empId.isEmpty()) {
            response.setStatus(400);
            responseDto.setResult(false);
            responseDto.setMessage("EMPTY Emp ID");
            return responseDto;
        }
        if (password == null || password.isEmpty()) {
            response.setStatus(400);
            responseDto.setResult(false);
            responseDto.setMessage("EMPTY Password");
            return responseDto;
        }

        AuthDto authDto = authService.empSignIn(empId, password);
        if (authDto != null) {
            SimpleDateFormat formatDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar time = Calendar.getInstance();
            String loginDt = formatDt.format(time.getTime());
            String token = jwtService.create(authDto.getEmpId(), authDto.getAuthority(), authDto.getEmpNm(), authDto.getCompCd(), loginDt);
            String refreshToken = jwtService.createRefresh(authDto.getEmpId(), authDto.getAuthority());
            response.setHeader(Constant.HEADER_KEY, token);
            responseDto.setResult(true);
            responseDto.setMessage(authDto.getInitUrl());
            //responseDto.setCompCd(authDto.getCompCd());
            EmpDto empDto = new EmpDto();
            empDto.setEmpId(empId);
            empDto.setToken(token);
            empDto.setRefreshToken(refreshToken);
            responseDto.setReturnDto(empDto);
            //responseDto.setToken(token);
            //responseDto.setRefreshToken(refreshToken);
            //DummyMessage dummyMessage = new DummyMessage("Login Success",1);
            //rabbitTemplate.convertAndSend("lane4.test", "lane4.key", dummyMessage);
        } else {
            responseDto.setResult(false);
            responseDto.setMessage("로그인 아이디나 비밀번호가 일치하지 않습니다.\n다시 확인하고 입력해 주세요.");
        }
        return responseDto;
    }


    /**
     * 토큰 재발급하기
     *
     * @param    {refreshToken}
     * @return ResponseDto
     */
    @RequestMapping(value = "/tokenRefresh", method = RequestMethod.POST)
    public ResponseApiDto tokenRefresh(HttpServletResponse response) {
        logger.debug("tokenRefresh");

        AuthDto authDto = jwtService.getRefreshAuthDto(); // 헤더에 포함된 리프레시 토큰으로 AuthDto 로 만들어온다.
        ResponseApiDto responseDto = new ResponseApiDto();
        if (authDto != null) { // authDto 가 null 이 아니면 토큰에서 정상적으로 디코딩이 되었다.
            String token = "";
            SimpleDateFormat formatDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar time = Calendar.getInstance();
            String loginDt = formatDt.format(time.getTime());

            //권한이 사용자인 경우
            if (Constant.ROLE_USER.equals(authDto.getAuthority())) {
                String userId = authDto.getUserId();
                SvcUserDto user = authService.getUserInfo(userId, "0");
                token = jwtService.create(userId, Constant.ROLE_USER, user.getUserNm(), user.getCompCd(), loginDt); // 토큰을 새로 만든다.
                user.setToken(token);
                responseDto.setData(user);
                responseDto.setMessage("SUCCESS");

                SvcUserDto loginDto = new SvcUserDto();
                loginDto.setUserId(Integer.parseInt(userId));
                loginDto.setToken(token);
                loginDto.setLastLoginDt(loginDt);
                loginDto.setVerNo(user.getVerNo());
                userRepository.updSvcUser(loginDto); // 토큰저장
            }
            //어드민인 경우
            else {
                EmpDto dto = new EmpDto();
                //String compCd = authDto.getCompCd();
                String compCd = "LANE4";
                String empId = authDto.getEmpId();
                dto.setCompCd(compCd);
                dto.setEmpId(empId);

                token = jwtService.create(empId, authDto.getAuthority(), authDto.getEmpNm(), authDto.getCompCd(), loginDt);

                EmpDto emp = authService.getEmpInfo(dto);
                //emp.setToken(token);
                //responseDto.setData(emp);
                //responseDto.setMessage(emp.getInitUrl());
            }
            response.setHeader(Constant.HEADER_KEY, token);
            responseDto.setResult(true);
        }
        //authDto가 null인 경우
        else {
            response.setStatus(401);
            responseDto.setResult(false);
            responseDto.setMessage("NO ID");
        }
        return responseDto;
    }

    @RequestMapping(value = "/checkExpiredPage", method = RequestMethod.POST)
    public ResponseApiDto checkExpiredPage() {
        logger.debug("checkExpiredPage");

        boolean result = false;
        ResponseApiDto responseDto = new ResponseApiDto();
        if (jwtService.checkExpiredPage() != null) {
            result = true;
        }
        responseDto.setResult(result);
        return responseDto;
    }
}