package com.isaguler.dataexportdemo.service;

import com.isaguler.dataexportdemo.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelService {

    private final PersonService personService;

    public byte[] exportExcel() {
        List<Person> personList = personService.findAll();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Persons");
            sheet.setColumnWidth(0, 3000);
            sheet.setColumnWidth(1, 4000);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 4000);

            Row header = sheet.createRow(0);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);
            headerStyle.setFont(font);

            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("###");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(1);
            headerCell.setCellValue("Firstname");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(2);
            headerCell.setCellValue("Lastname");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(3);
            headerCell.setCellValue("Age");
            headerCell.setCellStyle(headerStyle);

            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);

            for (int i = 1; i <= personList.size(); i++) {
                Row row = sheet.createRow(i);
                Cell cell = row.createCell(0);
                cell.setCellValue(i);
                cell.setCellStyle(style);

                cell = row.createCell(1);
                cell.setCellValue(personList.get(i - 1).getFirstname());
                cell.setCellStyle(style);

                cell = row.createCell(2);
                cell.setCellValue(personList.get(i - 1).getLastname());
                cell.setCellStyle(style);

                cell = row.createCell(3);
                cell.setCellValue(personList.get(i - 1).getAge());
                cell.setCellStyle(style);

            }

            workbook.write(byteArrayOutputStream);
            workbook.close();

        } catch (Exception e) {
            log.info("exportExcel exception: " + e.getMessage());
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }
}
