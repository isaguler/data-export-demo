package com.isaguler.dataexportdemo.service;

import com.isaguler.dataexportdemo.model.Person;
import com.isaguler.dataexportdemo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WordService {

    private final PersonService personService;

    public byte[] exportWord() {
        List<Person> personList = personService.findAll();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            XWPFDocument document = new XWPFDocument();

            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun titleRun = title.createRun();
            titleRun.setBold(true);
            titleRun.setFontSize(22);
            titleRun.setFontFamily("Courier");
            titleRun.setText("Person List (All)");

            XWPFTable table1 = document.createTable();

            XWPFTableRow row1 = table1.getRow(0);
            row1.getCell(0).setText("#");
            row1.addNewTableCell().setText("Firstname");
            row1.addNewTableCell().setText("Lastname");
            row1.addNewTableCell().setText("Age");

            for (int i = 1; i <= personList.size(); i++) {
                XWPFTableRow rowi = table1.createRow();
                rowi.getCell(0).setText(String.valueOf(i));
                rowi.getCell(1).setText(personList.get(i - 1).getFirstname());
                rowi.getCell(2).setText(personList.get(i - 1).getLastname());
                rowi.getCell(3).setText(personList.get(i - 1).getAge().toString());
            }

            document.write(byteArrayOutputStream);

        } catch (Exception e) {
            log.info("exportWord exception: " + e.getMessage());
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }
}
