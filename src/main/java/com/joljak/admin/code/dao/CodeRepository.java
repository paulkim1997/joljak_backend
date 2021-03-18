package com.joljak.admin.code.dao;

import java.util.List;

import com.joljak.dto.CodeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.joljak.dto.GroupCodeDto;

@Mapper
@Repository
public interface CodeRepository {
    // Group Code
    public List<GroupCodeDto> selGroupCodeList();

    public int cntGroupCode(String groupCode);

    public void insGroupCode(GroupCodeDto groupCodeDto);

    public void updGroupCode(GroupCodeDto groupCodeDto);

    public void delGroupCode(GroupCodeDto groupCodeDto);

    // Detail Coe
    public List<CodeDto> selDetailCodeList(String groupCode);

    public int cntGroupInDetailCode(String groupCode);

    public int cntDetailCode(String detailCode);

    public int nxtDetailCodeOrder(String groupCode);

    public void insDetailCode(CodeDto codeDto);

    public void updDetailCode(CodeDto codeDto);

    public void delDetailCode(CodeDto codeDto);

    public void updCodeOrdering(CodeDto codeDto);

    public List<GroupCodeDto> selGroupCodeInDetailCodeList(GroupCodeDto codeDto);
}