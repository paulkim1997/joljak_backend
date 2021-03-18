package com.joljak.auth.dao;

import com.joljak.dto.*;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AuthRepository {
    public AuthDto empSignIn(String value);

    public AuthDto drvSignIn(String value);

    public EmpDto getEmpInfo(EmpDto dto);

    public String getDrvToken(String value);

    public SvcUserDto getUserInfo(String value);

    public String getSvcUserToken(String value);

    public String getDrvRefreshToken(String value);

    public String getSvcUserRefreshToken(String value);

    public int insAuthNo(AuthNoDto authNoDto);
}