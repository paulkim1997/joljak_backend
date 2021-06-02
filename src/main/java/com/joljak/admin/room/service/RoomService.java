package com.joljak.admin.room.service;

import com.joljak.base.dto.DataTablesResponse;
import com.joljak.dto.EmpDto;
import com.joljak.dto.RoomDto;
import com.joljak.dto.SearchDto;
import com.joljak.dto.SvcUserDto;

import java.util.concurrent.ExecutionException;

public interface RoomService {
    //EMP
    public DataTablesResponse<RoomDto> selRoomInfo(SearchDto dto);

    public DataTablesResponse<RoomDto> selAllRoomInfo();

    public DataTablesResponse<RoomDto> selRoomStatistics(SearchDto dto);

/*
    public boolean dupCheckEmp(EmpDto dto);

    public boolean insEmp(EmpDto dto);

    public boolean updEmp(EmpDto dto);

    public boolean delEmp(EmpDto dto);

    //SVC_USER
    public DataTablesResponse<SvcUserDto> selSvcUserList(SearchDto dto);

    public boolean dupCheckSvcUser(SvcUserDto dto);

    public boolean insSvcUser(SvcUserDto dto);

    public boolean updSvcUser(SvcUserDto dto);

    public boolean delSvcUser(SvcUserDto dto);

    public SvcUserDto getUserInfo(SvcUserDto dto);

    public void updSvcUserPwd(SvcUserDto dto);
*/
}