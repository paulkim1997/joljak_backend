package com.joljak.admin.room.dao;

import com.joljak.dto.EmpDto;
import com.joljak.dto.RoomDto;
import com.joljak.dto.SearchDto;
import com.joljak.dto.SvcUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoomRepository {
    //Emp
    public int cntTotalEmp(SearchDto dto);

    public int cntFilterdEmp(SearchDto dto);

    public List<RoomDto> selRoomInf(SearchDto dto);

    public List<RoomDto> selAllRoomInf();

    public void insEmp(EmpDto dto);

    public void updEmp(EmpDto dto);

    //SvcUser
    public int cntTotalSvcUserByMobile(SearchDto dto);

    public int cntTotalSvcUserByEmail(SearchDto dto);

    public int cntFilterdSvcUser(SearchDto dto);

    public List<SvcUserDto> selSvcUserList(SearchDto dto);

    public int insSvcUser(SvcUserDto dto);

    public int updSvcUser(SvcUserDto dto);

    public int updSvcUserPwd(SvcUserDto dto);

    public int signOutSvcUser(SvcUserDto dto);

    public SvcUserDto getUserInfo(SvcUserDto dto);

    public int cntLeaveSvcUser(SearchDto dto);

    public int updSvcUserLeave(SvcUserDto dto);

    public int insLeaveUser(SvcUserDto dto);

}