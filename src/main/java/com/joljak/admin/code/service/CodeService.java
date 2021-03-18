package com.joljak.admin.code.service;

import java.util.List;

import com.joljak.dto.GroupCodeDto;
import com.joljak.dto.CodeDto;

public interface CodeService {
    public List<GroupCodeDto> selGroupCodeList();

    public boolean insGroupCode(GroupCodeDto groupCodeDto);

    public boolean updGroupCode(GroupCodeDto groupCodeDto);

    public boolean delGroupCode(GroupCodeDto groupCodeDto);

    public List<CodeDto> selDetailCodeList(String groupCode);

    public boolean insDetailCode(CodeDto codeDto);

    public boolean updDetailCode(CodeDto codeDto);

    public boolean delDetailCode(CodeDto codeDto);

    public boolean updCodeOrdering(List<CodeDto> codeDtoList);

    public List<CodeDto> selAllDetailCodeList();

    public List<GroupCodeDto> selGroupCodeInDetailCodeList(GroupCodeDto codeDto);
}