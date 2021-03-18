package com.joljak.admin.code.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.joljak.admin.code.service.CodeService;
import com.joljak.dto.AuthDto;
import com.joljak.auth.service.JwtService;
import com.joljak.base.constant.Constant;
import com.joljak.base.dto.ResponseDto;
import com.joljak.dto.CodeDto;
import com.joljak.dto.GroupCodeDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// import io.swagger.annotations.Api;

/**
 * @author 김용희
 * @version 0.1
 * @Class Name : CodeController.java
 * @Description : 시스템 코드 정보 관련 Controller Class
 * @Modification Information
 * @
 * @ 수정일        수정자              수정내용
 * @ ---------    ---------   -------------------------------
 * @ 2019.08.17   [김용희]      최초생성
 * @since 2019.08.17
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/code")
// @Api(value = "CodeController", description = "시스템 코드 정보 관련 Controller")
public class CodeController {

    private static Logger logger = LoggerFactory.getLogger(CodeController.class);

    @Resource(name = "codeService")
    private CodeService codeService;

    @Autowired
    private JwtService jwtService;

    ////////////// GROUP CODE //////////////

    /**
     * 그룹코드 목록 가져오기
     *
     * @param
     * @return List<GroupCodeDto>
     */
    @RequestMapping(value = "/selGroupCodeList", method = RequestMethod.GET)
    public List<GroupCodeDto> selGroupCodeList() {
        logger.debug("selGroupCodeList");
        List<GroupCodeDto> selectList = codeService.selGroupCodeList(); // 그룹코드 관리는 paging 기능을 이용하지 않는다. 필요한가?
        return selectList;
    }

    /**
     * 그룹코드 추가하기
     *
     * @param    {GRP_CD, GRP_CD_NM}
     * @return String (결과 형식 설계 필요)
     */
    @RequestMapping(value = "/insGroupCode", method = RequestMethod.POST)
    public ResponseDto insGroupCode(@RequestBody GroupCodeDto groupCodeDto, HttpServletResponse response) {
        logger.debug("insGroupCode");
        ResponseDto responseDto = new ResponseDto();
        String grpCd = groupCodeDto.getNewCd(); // 그룹코드 등록시 이 2개의 파라메터는 필수로 간주
        String grpCdNm = groupCodeDto.getGrpCdNm();
        if (grpCd == null || grpCd.isEmpty()) { // 차후 파라메터 부족에 대한 결과를 넣을 경우를 위해 if 를 각각 쓰자. 뭐 더 좋은 방법 없나??
            response.setStatus(400);
            responseDto.setResult(false);
            responseDto.setMessage("EMPTY Group Code");
            return responseDto;
        }
        if (grpCdNm == null || grpCdNm.isEmpty()) {
            response.setStatus(400);
            responseDto.setResult(false);
            responseDto.setMessage("EMPTY Group Code Name");
            return responseDto;
        }
        AuthDto authDto = jwtService.getAuthDto();
        groupCodeDto.setRegId(authDto.getEmpId());

        groupCodeDto.setStatus(Constant.NORMAL);
        boolean result = codeService.insGroupCode(groupCodeDto);
        responseDto.setResult(result);
        responseDto.setMessage(result ? "SUCCESS" : "DUPLICATE CODE");
        return responseDto;
    }

    /**
     * @ 그룹코드 수정하기
     * @param    {grpCd, newCd, grpCdNm}
     * @return String
     */
    @RequestMapping(value = "/updGroupCode", method = RequestMethod.POST)
    public ResponseDto updGroupCode(@RequestBody GroupCodeDto groupCodeDto, HttpServletResponse response) {
        logger.debug("updGroupCode");
        ResponseDto responseDto = new ResponseDto();
        String grpCd = groupCodeDto.getGrpCd(); // 그룹코드 등록시 이 2개의 파라메터는 필수로 간주
        if (grpCd == null || grpCd.isEmpty()) { // 차후 파라메터 부족에 대한 결과를 넣을 경우를 위해 if 를 각각 쓰자. 뭐 더 좋은 방법 없나??
            response.setStatus(400);
            responseDto.setResult(false);
            responseDto.setMessage("EMPTY Group Code");
            return responseDto;
        }
        AuthDto authDto = jwtService.getAuthDto();
        groupCodeDto.setModId(authDto.getEmpId());

        boolean result = codeService.updGroupCode(groupCodeDto);
        responseDto.setResult(result);
        responseDto.setMessage(result ? "SUCCESS" : "FAIL");
        return responseDto;
    }

    /**
     * 그룹코드에 속하는 상세코드 목록 가져오기
     *
     * @param
     * @return List<CodeDto>
     */
    @RequestMapping(value = "/selDetailCodeList/{grpCd}", method = RequestMethod.GET)
    public List<CodeDto> selDetailCodeList(@ModelAttribute CodeDto codeDto, HttpServletResponse response) {
        logger.debug("selDetailCodeList");
        String grpCd = codeDto.getGrpCd();
        if (grpCd == null || grpCd.isEmpty()) {
            response.setStatus(400);
            return null;
        }
        List<CodeDto> selectList = codeService.selDetailCodeList(grpCd); // 그룹코드 관리는 paging 기능을 이용하지 않는다. 필요한가?
        return selectList;
    }

    /**
     * 상세코드 추가하기
     *
     * @param    {grpCd, dtlCd, dtlCdNm, val1, val2, val3, dtlOrder}
     * @return String
     */
    @RequestMapping(value = "/insDetailCode", method = RequestMethod.POST)
    public ResponseDto insDetailCode(@RequestBody CodeDto codeDto, HttpServletResponse response) {
        logger.debug("insDetailCode");
        String grpCd = codeDto.getGrpCd(); // 상세코드 등록시 이 3개의 파라메터는 필수로 간주
        String dtlCd = codeDto.getNewCd();
        String dtlCdNm = codeDto.getDtlCdNm();

        ResponseDto responseDto = new ResponseDto();
        if (grpCd == null || grpCd.isEmpty()) { // 차후 파라메터 부족에 대한 결과를 넣을 경우를 위해 if 를 각각 쓰자. 뭐 더 좋은 방법 없나??
            response.setStatus(400);
            responseDto.setResult(false);
            responseDto.setMessage("EMPTY Group Code");
            return responseDto;
        }
        if (dtlCd == null || dtlCd.isEmpty()) {
            response.setStatus(400);
            responseDto.setResult(false);
            responseDto.setMessage("EMPTY Code");
            return responseDto;
        }
        if (dtlCdNm == null || dtlCdNm.isEmpty()) {
            response.setStatus(400);
            responseDto.setResult(false);
            responseDto.setMessage("EMPTY Code Name");
            return responseDto;
        }
        AuthDto authDto = jwtService.getAuthDto();
        codeDto.setRegId(authDto.getEmpId());

        codeDto.setStatus(codeDto.getStatus());
        boolean result = codeService.insDetailCode(codeDto);
        responseDto.setResult(result);
        responseDto.setMessage(result ? "SUCCESS" : "DUPLICATE CODE");
        return responseDto;
    }

    /**
     * 상세코드 수정하기
     *
     * @param    {GRP_CD, DTL_CD, NEW_CD, DTL_CD_NM, VAL1, VAL2, VAL3, DTL_ORDER}
     * @return String
     */
    @RequestMapping(value = "/updDetailCode", method = RequestMethod.POST)
    public ResponseDto updDetailCode(@RequestBody CodeDto codeDto, HttpServletResponse response) {
        logger.debug("updDetailCode");
        ResponseDto responseDto = new ResponseDto();
        String grpCd = codeDto.getGrpCd();
        String dtlCd = codeDto.getDtlCd(); // 그룹코드 등록시 이 2개의 파라메터는 필수로 간주

        if (grpCd == null || grpCd.isEmpty()) { // 차후 파라메터 부족에 대한 결과를 넣을 경우를 위해 if 를 각각 쓰자. 뭐 더 좋은 방법 없나??
            response.setStatus(400);
            responseDto.setResult(false);
            responseDto.setMessage("EMPTY Group Code");
            return responseDto;
        }
        if (dtlCd == null || dtlCd.isEmpty()) { // 차후 파라메터 부족에 대한 결과를 넣을 경우를 위해 if 를 각각 쓰자. 뭐 더 좋은 방법 없나??
            response.setStatus(400);
            responseDto.setResult(false);
            responseDto.setMessage("EMPTY Code");
            return responseDto;
        }
        AuthDto authDto = jwtService.getAuthDto();
        codeDto.setModId(authDto.getEmpId());

        boolean result = codeService.updDetailCode(codeDto);
        responseDto.setResult(result);
        responseDto.setMessage(result ? "SUCCESS" : "FAIL");
        return responseDto;
    }

    /**
     * 상세코드 순서 변경하기
     *
     * @param    {GRP_CD, DTL_CD, NEW_CD, DTL_CD_NM, VAL1, VAL2, VAL3, DTL_ORDER}
     * @return String
     */
    @RequestMapping(value = "/updCodeOrdering", method = RequestMethod.POST)
    public ResponseDto updCodeOrdering(@RequestBody List<CodeDto> codeDtoList, HttpServletResponse response) {
        logger.debug("updCodeOrdering");
        ResponseDto responseDto = new ResponseDto();
        AuthDto authDto = jwtService.getAuthDto();
        for (CodeDto codeDto : codeDtoList) {
            String grpCd = codeDto.getGrpCd();
            String dtlCd = codeDto.getDtlCd(); // 그룹코드 등록시 이 2개의 파라메터는 필수로 간주
            int dtlOrder = codeDto.getDtlOrder();

            if (grpCd == null || grpCd.isEmpty()) { // 차후 파라메터 부족에 대한 결과를 넣을 경우를 위해 if 를 각각 쓰자. 뭐 더 좋은 방법 없나??
                response.setStatus(400);
                responseDto.setResult(false);
                responseDto.setMessage("EMPTY Group Code");
                return responseDto;
            }
            if (dtlCd == null || dtlCd.isEmpty()) { // 차후 파라메터 부족에 대한 결과를 넣을 경우를 위해 if 를 각각 쓰자. 뭐 더 좋은 방법 없나??
                response.setStatus(400);
                responseDto.setResult(false);
                responseDto.setMessage("EMPTY Code");
                return responseDto;
            }
            if (dtlOrder < 0) {
                response.setStatus(400);
                responseDto.setResult(false);
                responseDto.setMessage("EMPTY order value");
                return responseDto;
            }
            codeDto.setModId(authDto.getEmpId());
        }

        boolean result = codeService.updCodeOrdering(codeDtoList);
        responseDto.setResult(result);
        responseDto.setMessage(result ? "SUCCESS" : "FAIL");
        return responseDto;
    }

    ////////////// 코드관리가 아닌 어플리케이션에서 코드ㅡ 사용을 위한 부분 //////////////

    /**
     * 상세코드를 전부 리턴한다.
     * 상세코드를 key 로 매핑하는것은 프론트엔드에서 수행한다.
     *
     * @param
     * @return List<GroupCodeDto>
     */
    @RequestMapping(value = "/selAllDetailCodeList", method = RequestMethod.GET)
    public List<CodeDto> selAllDetailCodeList() {
        logger.debug("selAllDetailCodeList");
        List<CodeDto> selectList = codeService.selAllDetailCodeList(); // 그룹코드 관리는 paging 기능을 이용하지 않는다. 필요한가?
        return selectList;
    }

    /**
     * 그룹코드에 대한 정보를 조회하면서 그룹코드에 속하는
     * 상세코드 목록을 붙여서 리턴한다.
     *
     * @param
     * @return List<GroupCodeDto>
     */
    @RequestMapping(value = "/selGroupCodeInDetailCodeList")
    public List<GroupCodeDto> selGroupCodeInDetailCodeList(@RequestBody GroupCodeDto codeDto) {
        logger.debug("selGroupCodeInDetailCodeList");
        List<GroupCodeDto> groupCodeList = codeService.selGroupCodeInDetailCodeList(codeDto); // 그룹코드 관리는 paging 기능을 이용하지 않는다. 필요한가?
        return groupCodeList;
    }

}