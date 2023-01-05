package com.isaguler.dataexportdemo.controller;

import com.isaguler.dataexportdemo.dto.PersonDto;
import com.isaguler.dataexportdemo.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/add/person")
    public void addPerson(@RequestBody PersonDto personDto) {
        try {
            personService.addPerson(personDto);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
