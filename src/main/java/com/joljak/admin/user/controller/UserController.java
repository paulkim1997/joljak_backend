package com.joljak.admin.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.joljak.dto.*;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;

import com.joljak.auth.service.JwtService;
import com.joljak.base.constant.Constant;
import com.joljak.base.dto.DataTablesResponse;
import com.joljak.base.dto.ResponseDto;
import com.joljak.admin.user.service.UserService;

/**
 * @author
 * @Class Name : UserController.java
 * @Description : 관리자 Controller Class
 * @Modification Information
 * @ @ 수정일 수정자 수정내용 @ --------- --------- ------------------------------- @
 * 2020.09.25 [] 최초생성
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/user")

@Api(value = "UserController", description = "사용자(시스템이용자), 회원(기사), 고객(기업) 을 관리하는 기능을 담은 Controller")

public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @ApiOperation(value = "임직원 조회", notes = "임직원 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/selEmpList", method = RequestMethod.GET)
    public DataTablesResponse<EmpDto> selEmpList(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @ModelAttribute SearchDto dto) {
        logger.debug("selEmpList");
        DataTablesResponse<EmpDto> selectList = userService.selEmpList(dto);
        return selectList;
    }

    @ApiOperation(value = "임직원 등록", notes = "임직원 등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/insEmp", method = RequestMethod.POST)
    public ResponseDto insEmp(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @RequestBody EmpDto dto, HttpServletResponse response) {
        logger.debug("insEmp");
        AuthDto authDto = jwtService.getAuthDto();
        dto.setRegId(authDto.getEmpId());
        dto.setStatus(Constant.NORMAL);
        //등록 수행
        boolean result = userService.insEmp(dto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(result);
        responseDto.setMessage(Constant.SUCCESS);
        return responseDto;
    }

    @ApiOperation(value = "임직원 중복체크", notes = "중복체크")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/dupCheckEmp", method = RequestMethod.POST)
    public ResponseDto dupCheckEmp(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @RequestBody EmpDto dto, HttpServletResponse response) {
        //중복체크
        boolean result = userService.dupCheckEmp(dto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(result);
        responseDto.setMessage(Constant.SUCCESS);
        return responseDto;
    }

    @ApiOperation(value = "임직원 수정", notes = "임직원 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/updEmp", method = RequestMethod.POST)
    public ResponseDto updEmp(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @RequestBody EmpDto dto, HttpServletResponse response) {
        logger.debug("updEmp");
        boolean result = userService.updEmp(dto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(result);
        responseDto.setMessage(Constant.SUCCESS);
        return responseDto;
    }

    @ApiOperation(value = "비밀번호만 수정", notes = "비밀번호만 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/changePw", method = RequestMethod.POST)
    public ResponseDto changPw(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @RequestBody EmpDto dto, HttpServletResponse response) {
        logger.debug("updEmp");
        boolean result = userService.changePw(dto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(result);
        responseDto.setMessage(Constant.SUCCESS);
        return responseDto;
    }

    @ApiOperation(value = "임직원 삭제", notes = "임직원 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/delEmp", method = RequestMethod.POST)
    public ResponseDto delEmp(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @RequestBody EmpDto dto, HttpServletResponse response) {
        logger.debug("delEmp");
        dto.setStatus(Constant.DELETE);
        boolean result = userService.delEmp(dto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(result);
        responseDto.setMessage(Constant.SUCCESS);
        return responseDto;
    }

    @ApiOperation(value = "사용자 조회", notes = "사용자 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/selSvcUserList", method = RequestMethod.GET)
    public DataTablesResponse<SvcUserDto> selSvcUserList(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @ModelAttribute SearchDto dto) {
        logger.debug("selSvcUserList");
        DataTablesResponse<SvcUserDto> selectList = userService.selSvcUserList(dto);
        return selectList;
    }

    @ApiOperation(value = "사용자 등록", notes = "사용자 등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/insSvcUser", method = RequestMethod.POST)
    public ResponseDto insSvcUser(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @RequestBody SvcUserDto dto, HttpServletResponse response) {
        logger.debug("insSvcUser");
        AuthDto authDto = jwtService.getAuthDto();
        dto.setRegId(authDto.getEmpId());
        dto.setStatus(Constant.NORMAL);
        //등록 수행
        boolean result = userService.insSvcUser(dto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(result);
        responseDto.setMessage(Constant.SUCCESS);
        return responseDto;
    }

    @ApiOperation(value = "사용자 중복체크", notes = "사용자 중복체크")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/dupCheckSvcUser", method = RequestMethod.POST)
    public ResponseDto dupCheckSvcUser(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @RequestBody SvcUserDto dto, HttpServletResponse response) {
        //중복체크
        boolean result = userService.dupCheckSvcUser(dto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(result);
        responseDto.setMessage(Constant.SUCCESS);
        return responseDto;
    }

    @ApiOperation(value = "사용자 수정", notes = "사용자 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/updSvcUser", method = RequestMethod.POST)
    public ResponseDto updSvcUser(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @RequestBody SvcUserDto dto, HttpServletResponse response) {
        logger.debug("updSvcUser");
        boolean result = userService.updSvcUser(dto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(result);
        responseDto.setMessage(Constant.SUCCESS);
        return responseDto;
    }

    @ApiOperation(value = "사용자 삭제", notes = "사용자 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/delSvcUser", method = RequestMethod.POST)
    public ResponseDto delSvcUser(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @RequestBody SvcUserDto dto, HttpServletResponse response) {
        logger.debug("delSvcUser");
        dto.setStatus(Constant.DELETE);
        boolean result = userService.delSvcUser(dto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(result);
        responseDto.setMessage(Constant.SUCCESS);
        return responseDto;
    }


}