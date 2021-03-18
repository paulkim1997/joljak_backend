package com.joljak.auth.service;

import com.joljak.dto.*;

public interface AuthService {
    public AuthDto empSignIn(String empId, String password);

    public EmpDto getEmpInfo(EmpDto dto);

    public SvcUserDto getUserInfo(String userId, String onLineYn);
}