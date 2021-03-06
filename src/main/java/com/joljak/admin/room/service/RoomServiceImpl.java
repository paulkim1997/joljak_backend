package com.joljak.admin.room.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.joljak.admin.room.dao.RoomRepository;
import com.joljak.auth.dao.AuthRepository;
import com.joljak.base.dto.DataTablesResponse;
import com.joljak.base.error.ErrorCode;
import com.joljak.base.exception.BusinessException;
import com.joljak.dto.EmpDto;
import com.joljak.dto.RoomDto;
import com.joljak.dto.SearchDto;
import com.joljak.dto.SvcUserDto;
import com.joljak.firebase.FireBaseInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static com.joljak.base.error.ErrorCode.UPDATE_ERROR;
import static com.joljak.base.error.ErrorCode.USER_NOT_FOUND;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    AuthRepository authRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public DataTablesResponse<RoomDto> selRoomInfo(SearchDto dto) {
        DataTablesResponse<RoomDto> dataTableResult = new DataTablesResponse<>();
        dataTableResult.setArticleList(roomRepository.selRoomInf(dto));
        return dataTableResult;
    }

    @Override
    public DataTablesResponse<RoomDto> selAllRoomInfo() {
        DataTablesResponse<RoomDto> dataTableResult = new DataTablesResponse<>();
        dataTableResult.setArticleList(roomRepository.selAllRoomInf());
        return dataTableResult;
    }

    @Override
    public DataTablesResponse<RoomDto> selRoomStatistics(SearchDto dto) {
        DataTablesResponse<RoomDto> dataTableResult = new DataTablesResponse<>();
        dataTableResult.setArticleList(roomRepository.selRoomStatistics(dto));
        return dataTableResult;
    }

/*
    @Override
    public boolean insEmp(EmpDto dto) {
        //???????????? ???????????? Known Exception ????????? ????????????
        if (dto.getEmpId() == null || dto.getEmpId().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "empId");
        }
        if (dto.getStatus() == null || dto.getStatus().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "?????????");
        }
        SearchDto inDto = new SearchDto();
        inDto.setEmpId(dto.getEmpId());
        inDto.setStatus(dto.getStatus());
        int chkCount = userRepository.cntTotalEmp(inDto);
        if (chkCount > 0) {
            throw new BusinessException(ErrorCode.DUP_CHK_ERROR, "emId");
        }
        String rawPassword = dto.getPwd();
        //String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        //dto.setPwd(encodedPassword);
        dto.setPwd(rawPassword);

        userRepository.insEmp(dto);
        return true;
    }

    @Override
    public boolean updEmp(EmpDto dto) {
        //???????????? ???????????? Known Exception ????????? ????????????
        if (dto.getEmpId() == null || dto.getEmpId().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "empId");
        }
        if (dto.getChkStatus() == null || dto.getChkStatus().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "?????????");
        }
        if (dto.getVerNo() < 1) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "????????????");
        }

        String rawPassword = dto.getPwd();
        if (rawPassword != null && !rawPassword.isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
            dto.setPwd(encodedPassword);
        }
        userRepository.updEmp(dto);
        return true;
    }

    @Override
    public boolean delEmp(EmpDto dto) {
        //???????????? ???????????? Known Exception ????????? ????????????
        if (dto.getEmpId() == null || dto.getEmpId().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "empId");
        }
        if (dto.getChkStatus() == null || dto.getChkStatus().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "?????????");
        }
        if (dto.getVerNo() < 1) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "????????????");
        }
        userRepository.updEmp(dto);
        return true;
    }

    @Override
    public boolean dupCheckEmp(EmpDto dto) {
        //???????????? ???????????? Known Exception ????????? ????????????
        if (dto.getEmpId() == null || dto.getEmpId().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "empId");
        }
        if (dto.getStatus() == null || dto.getStatus().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "?????????");
        }
        SearchDto inDto = new SearchDto();
        inDto.setEmpId(dto.getEmpId());
        inDto.setStatus(dto.getStatus());
        int chkCount = userRepository.cntTotalEmp(inDto);
        return true;
    }

    @Override
    public DataTablesResponse<SvcUserDto> selSvcUserList(SearchDto dto) {
        DataTablesResponse<SvcUserDto> dataTableResult = new DataTablesResponse<SvcUserDto>();
        //dataTableResult.setRecordsTotal(userRepository.cntTotalSvcUserByMobile(null));
        dataTableResult.setRecordsFiltered(userRepository.cntFilterdSvcUser(dto));
        dataTableResult.setArticleList(userRepository.selSvcUserList(dto));
        return dataTableResult;
    }

    @Override
    public boolean insSvcUser(SvcUserDto dto) {
        //???????????? ???????????? Known Exception ????????? ????????????
        if (dto.getUserMobile() == null || dto.getUserMobile().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "????????? ????????? ??????");
        }
        if (dto.getStatus() == null || dto.getStatus().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "?????????");
        }
        SearchDto inDto = new SearchDto();
        inDto.setUserMobile(dto.getUserMobile());
        inDto.setStatus(dto.getStatus());
        int chkCount = userRepository.cntTotalSvcUserByMobile(inDto);
        if (chkCount > 0) {
            throw new BusinessException(ErrorCode.DUP_CHK_ERROR, "???????????????");
        }
        String rawPassword = dto.getPwd();
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        dto.setPwd(encodedPassword);
        userRepository.insSvcUser(dto);
        return true;
    }

    @Override
    public boolean updSvcUser(SvcUserDto dto) {
        //???????????? ???????????? Known Exception ????????? ????????????
        if (dto.getUserMobile() == null || dto.getUserMobile().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "????????? ????????? ??????");
        }
        if (dto.getChkStatus() == null || dto.getChkStatus().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "?????????");
        }
        if (dto.getVerNo() < 1) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "????????????");
        }

        String rawPassword = dto.getPwd();
        if (rawPassword != null && !rawPassword.isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
            dto.setPwd(encodedPassword);
        }
        userRepository.updSvcUser(dto);
        return true;
    }

    @Override
    public boolean delSvcUser(SvcUserDto dto) {
        //???????????? ???????????? Known Exception ????????? ????????????
        if (dto.getUserMobile() == null || dto.getUserMobile().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "????????? ????????? ??????");
        }
        if (dto.getChkStatus() == null || dto.getChkStatus().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "?????????");
        }
        if (dto.getVerNo() < 1) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "????????????");
        }
        userRepository.updSvcUser(dto);
        return true;
    }


    @Override
    public boolean dupCheckSvcUser(SvcUserDto dto) {
        //???????????? ???????????? Known Exception ????????? ????????????
        if (dto.getUserMobile() == null || dto.getUserMobile().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "????????? ????????? ??????");
        }
        if (dto.getStatus() == null || dto.getStatus().isEmpty()) {
            throw new BusinessException(ErrorCode.REQUIRED_MANDATORY, "?????????");
        }
        SearchDto inDto = new SearchDto();
        inDto.setUserMobile(dto.getUserMobile());
        inDto.setStatus(dto.getStatus());
        int chkCount = userRepository.cntTotalSvcUserByMobile(inDto);
        return true;
    }


    @Override
    public SvcUserDto getUserInfo(SvcUserDto dto) {
        SvcUserDto userDto = userRepository.getUserInfo(dto);
        if (userDto == null) {
            throw new BusinessException(USER_NOT_FOUND);
        }
        return userDto;
    }

    @Override
    public void updSvcUserPwd(SvcUserDto dto) {
        int x = userRepository.updSvcUserPwd(dto);
        if (x == 0) {
            throw new BusinessException(UPDATE_ERROR, "????????????");
        }
    }*/
}