package com.isaguler.dataexportdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PersonDto{

    private String firstname;
    private String lastname;
    private Integer age;
}
