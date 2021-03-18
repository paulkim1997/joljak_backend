package com.joljak.xlsx.component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joljak.xlsx.constant.ExcelConstant;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("unchecked")
public class ExcelWriter {

    private Workbook workbook;
    private Map<String, Object> model;
    // private HttpServletRequest request;
    private HttpServletResponse response;

    public ExcelWriter(Workbook workbook, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        this.workbook = workbook;
        this.model = model;
        // this.request = request;
        this.response = response;
    }

    public void create() {
        response.setHeader("Content-Disposition", "attachment; filename=\"ReservationList\"");

        applyContentTypeForRequest();

        Sheet sheet = workbook.createSheet();

        createHead(sheet, mapToHeadList());

        createBody(sheet, mapToBodyList());
    }

    private List<String> mapToHeadList() {
        return (List<String>) model.get(ExcelConstant.HEAD);
    }

    private List<List<String>> mapToBodyList() {
        return (List<List<String>>) model.get(ExcelConstant.BODY);
    }

    private void applyContentTypeForRequest() {
        if (workbook instanceof XSSFWorkbook || workbook instanceof SXSSFWorkbook) {
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }
        if (workbook instanceof HSSFWorkbook) {
            response.setHeader("Content-Type", "application/vnd.ms-excel");
        }
    }

    private void createHead(Sheet sheet, List<String> headList) {
        createRow(sheet, headList, 0, 0);
    }

    private void createBody(Sheet sheet, List<List<String>> bodyList) {
        int rowSize = bodyList.size();
        for (int i = 0; i < rowSize; i++) {
            createRow(sheet, bodyList.get(i), i + 1, rowSize);
        }
        for (int i = 0; i < rowSize; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void createRow(Sheet sheet, List<String> cellList, int rowNum, int rowSize) {
        int size = cellList.size();
        Row row = sheet.createRow(rowNum);

        for (int i = 0; i < size; i++) {
            CellStyle cellStyle = workbook.createCellStyle();
            // Font cellFont = workbook.createFont();
            // cellStyle.setFont(cellFont);

            if (rowNum == 0) { // rowNum 이 0 은 헤더읻다.
                // cellFont.setBold(true);
                // cellFont.setFontHeight((short) 14);
                cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cellStyle.setBorderBottom(BorderStyle.DOUBLE);
                cellStyle.setBorderTop(BorderStyle.MEDIUM);
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
            } else {
                // cellFont.setFontHeight((short) 11);
                if (rowNum < rowSize) {
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                } else {
                    cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                }
            }

            Object value = cellList.get(i);
            Cell cell = row.createCell(i);

            if (value instanceof Integer) { // 정수 (승객수, 수화물수, 각종ID값, 회차 등)
                cell.setCellValue(value.toString());
                cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                // cell.setCellType(CellType.NUMERIC);
            } else if (value instanceof BigDecimal) { // 요금 (요금, 톨게이트요금, 주차요금, 피켓요금, 기타요금, 프로모션, 수수료, 지원금 등)
                cell.setCellValue(value.toString());
                cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                // cell.setCellType(CellType.NUMERIC);
            } else {
                cell.setCellValue((String) value);
                if (rowNum > 0) {
                    cellStyle.setAlignment(HorizontalAlignment.LEFT);
                }
            }
            if (i == 0) {
                cellStyle.setBorderLeft(BorderStyle.MEDIUM);
                cellStyle.setBorderRight(BorderStyle.THIN);
            } else if (i == (size - 1)) {
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.MEDIUM);
            } else {
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);
            }
            cell.getSheet().autoSizeColumn(i);
            cell.setCellStyle(cellStyle);
        }
    }
}