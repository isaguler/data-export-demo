package com.isaguler.dataexportdemo.controller;

import com.isaguler.dataexportdemo.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class DataExportController {

    private final ExcelService excelService;

    @GetMapping("/excel/all/person")
    public byte[] exportExcelAllPerson(HttpServletResponse response) {
        response.setHeader("Content-disposition","attachment; filename=person-list.xlsx");

        return excelService.exportExcel();
    }
}
