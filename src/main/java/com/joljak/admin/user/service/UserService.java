package com.joljak.admin.user.service;

import com.joljak.base.dto.DataTablesResponse;
import com.joljak.dto.*;

public interface UserService {
    //EMP
    public DataTablesResponse<EmpDto> selEmpList(SearchDto dto);

    public boolean dupCheckEmp(EmpDto dto);

    public boolean insEmp(EmpDto dto);

    public boolean updEmp(EmpDto dto);

    public boolean delEmp(EmpDto dto);

    public boolean changePw(EmpDto dto);

    //SVC_USER
    public DataTablesResponse<SvcUserDto> selSvcUserList(SearchDto dto);

    public boolean dupCheckSvcUser(SvcUserDto dto);

    public boolean insSvcUser(SvcUserDto dto);

    public boolean updSvcUser(SvcUserDto dto);

    public boolean delSvcUser(SvcUserDto dto);

    public SvcUserDto getUserInfo(SvcUserDto dto);

    public void updSvcUserPwd(SvcUserDto dto);

}