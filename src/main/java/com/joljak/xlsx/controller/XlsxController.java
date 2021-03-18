package com.joljak.xlsx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.joljak.dto.*;
import com.joljak.admin.code.service.CodeService;
import com.joljak.admin.user.service.UserService;
import com.joljak.xlsx.constant.ExcelConstant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/api/xlsx")
public class XlsxController {
    private static Logger logger = LoggerFactory.getLogger(XlsxController.class);

    @Resource(name = "codeService")
    private CodeService codeService;

    @Resource(name = "userService")
    private UserService userService;

    static Map<String, String> detailCodeMap = null;

    /**
     * 예약 목록 조회하기
     *
     * @param    {}
     * @return List<ResvDto>
     */
    @RequestMapping(value = "/selResvList", method = RequestMethod.GET)
    public ModelAndView selResvList(@ModelAttribute SearchDto resvDto) {
        logger.debug("selResvList");
//        DataTablesResponse<ResvDto> selectList = resvService.selResvList(resvDto);
//        List<ResvDto> articleList = selectList.getArticleList();

        Map<String, Object> map = new HashMap<>();
//        map.put(ExcelConstant.HEAD, Arrays.asList("채널", "사업유형", "메일", "기사명", "연락처", "차량번호", "이용일시", "이용시간", "언어", "출발지", "도착지", "차량유형", "피켓", "후불", "상태"));
        ArrayList<ArrayList<Object>> bodyRows = new ArrayList<ArrayList<Object>>();
//        for (ResvDto dto : articleList) {
//            ArrayList<Object> row = new ArrayList<Object>();
//            row.add(getCodeName(dto.getChannel()));
//            row.add(getCodeName(dto.getBizType()));
//            row.add(dto.getReservEmail());
//            row.add(dto.getDrvNm());
//            row.add(dto.getMobileNo());
//            row.add(dto.getCarNo());
//            row.add(dto.getUseDate());
//            row.add(dto.getUseTime());
//            row.add(getCodeName(dto.getLanguage()));
//            row.add(dto.getDepartureInput());
//            row.add(dto.getDestInput());
//            row.add(getCodeName(dto.getCarType()));
//            row.add(getCodeName(dto.getPicketYn()));
//            row.add(getCodeName(dto.getPreDeferred()));
//            row.add(getCodeName(dto.getStatus()));
//            bodyRows.add(row);
//        }
        map.put(ExcelConstant.BODY, bodyRows);
        return new ModelAndView("excelXlsxView", map);
    }

    String getCodeName(String dtlCd) {
        if (detailCodeMap == null) {
            List<CodeDto> selectList = codeService.selAllDetailCodeList();
            detailCodeMap = new HashMap<String, String>();
            for (CodeDto codeDto : selectList) {
                detailCodeMap.put(codeDto.getDtlCd(), codeDto.getDtlCdNm());
            }
        }
        if (dtlCd == null || "".equals(dtlCd)) {
            return "";
        }
        return detailCodeMap.get(dtlCd);
    }
}