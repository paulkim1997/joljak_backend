package com.joljak.admin.room.controller;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.joljak.admin.room.service.RoomService;
import com.joljak.auth.service.JwtService;
import com.joljak.base.constant.Constant;
import com.joljak.base.dto.DataTablesResponse;
import com.joljak.base.dto.ResponseDto;
import com.joljak.dto.*;
import com.joljak.firebase.FireBaseInitializer;
import com.joljak.firebase.FireBaseInitializer;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
@RequestMapping(value = "/api/room")

@Api(value = "RoomController", description = "방 정보 조회 기능을 담은 Controller")

public class RoomController {

    private static Logger logger = LoggerFactory.getLogger(RoomController.class);
    @Autowired
    FireBaseInitializer db;
    @Resource(name = "roomService")
    private RoomService roomService;

    @ApiOperation(value = "방 정보 조회", notes = "방 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/selRoomInf", method = RequestMethod.GET)
    public DataTablesResponse<RoomDto> selRoomInf(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @ModelAttribute SearchDto dto) {
        logger.debug("selRoomInf");
        DataTablesResponse<RoomDto> selectList = roomService.selRoomInfo(dto);
        return selectList;
    }

    @ApiOperation(value = "방 정보 전체 조회", notes = "방 정보 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/selAllRoomInf", method = RequestMethod.GET)
    public DataTablesResponse<RoomDto> selAllRoomInf(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @ModelAttribute SearchDto dto) {
        logger.debug("selRoomInf");
        DataTablesResponse<RoomDto> selectList = roomService.selAllRoomInfo();
        return selectList;
    }

    @ApiOperation(value = "방 통계 정보 조회", notes = "방 통계 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "\n" + "\n" + ""),
    })
    @RequestMapping(value = "/selRoomStatistics", method = RequestMethod.GET)
    public DataTablesResponse<RoomDto> selRoomStatistics(
            @ApiParam(value = "필수 xxxx \n" + "필수 xxxx \n" + "\n")
            @ModelAttribute SearchDto dto) throws ExecutionException, InterruptedException {
        logger.debug("selRoomStatistics");

        DataTablesResponse<RoomDto> selectList = roomService.selRoomStatistics(dto);
        return selectList;
    }

   /* @ApiOperation(value = "임직원 등록", notes = "임직원 등록")
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
        boolean result = roomService.insEmp(dto);
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
        boolean result = roomService.dupCheckEmp(dto);
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
        boolean result = roomService.updEmp(dto);
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
        boolean result = roomService.delEmp(dto);
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
        DataTablesResponse<SvcUserDto> selectList = roomService.selSvcUserList(dto);
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
        boolean result = roomService.insSvcUser(dto);
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
        boolean result = roomService.dupCheckSvcUser(dto);
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
        boolean result = roomService.updSvcUser(dto);
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
        boolean result = roomService.delSvcUser(dto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(result);
        responseDto.setMessage(Constant.SUCCESS);
        return responseDto;
    }*/


}