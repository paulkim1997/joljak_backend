package com.joljak.admin.code.service;

import java.util.List;

import com.joljak.dto.CodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joljak.admin.code.dao.CodeRepository;
import com.joljak.dto.GroupCodeDto;

@Service("codeService")
public class CodeServiceImpl implements CodeService {

    @Autowired
    CodeRepository codeRepository;

    ////////////// GROUP CODE //////////////
    @Override
    public List<GroupCodeDto> selGroupCodeList() {
        return codeRepository.selGroupCodeList();
    }

    @Override
    public boolean insGroupCode(GroupCodeDto groupCodeDto) {
        String groupCode = groupCodeDto.getGrpCd();
        int duplicateCount = codeRepository.cntGroupCode(groupCode);
        if (duplicateCount == 0) {
            codeRepository.insGroupCode(groupCodeDto);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updGroupCode(GroupCodeDto groupCodeDto) {
        codeRepository.updGroupCode(groupCodeDto);
        return true;
    }

    @Override
    public boolean delGroupCode(GroupCodeDto groupCodeDto) {
        codeRepository.delGroupCode(groupCodeDto);
        return true;
    }

    ////////////// DETAIL CODE //////////////
    @Override
    public List<CodeDto> selDetailCodeList(String groupCode) {
        return codeRepository.selDetailCodeList(groupCode);
    }

    @Override
    public boolean insDetailCode(CodeDto codeDto) {
        String detailCode = codeDto.getDtlCd();
        String groupCode = codeDto.getGrpCd();
        int duplicateCount = codeRepository.cntDetailCode(detailCode);
        if (duplicateCount == 0) {
            int nextOrder = codeRepository.nxtDetailCodeOrder(groupCode);
            codeDto.setDtlOrder(nextOrder);
            codeRepository.insDetailCode(codeDto);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updDetailCode(CodeDto codeDto) {
        codeRepository.updDetailCode(codeDto);
        return true;
    }

    @Override
    public boolean delDetailCode(CodeDto codeDto) {
        codeRepository.delDetailCode(codeDto);
        return true;
    }

    @Override
    public boolean updCodeOrdering(List<CodeDto> codeDtoList) {
        for (CodeDto codeDto : codeDtoList) {
            codeRepository.updCodeOrdering(codeDto);
        }
        return true;
    }

    @Override
    public List<CodeDto> selAllDetailCodeList() {
        return codeRepository.selDetailCodeList(null);
    }

    @Override
    public List<GroupCodeDto> selGroupCodeInDetailCodeList(GroupCodeDto codeDto) {
        return codeRepository.selGroupCodeInDetailCodeList(codeDto);
    }
}
