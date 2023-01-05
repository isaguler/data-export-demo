package com.isaguler.dataexportdemo.service;

import com.isaguler.dataexportdemo.dto.PersonDto;
import com.isaguler.dataexportdemo.model.Person;
import com.isaguler.dataexportdemo.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @PostConstruct
    void init() {
        //this.addInitPersonToDb();
    }

    public void addPerson(PersonDto personDto) {
        personRepository.saveAndFlush(this.convertDtoToModel(personDto));
    }

    public Person convertDtoToModel(PersonDto personDto) {
        Person person = new Person();
        person.setFirstname(personDto.getFirstname());
        person.setLastname(personDto.getLastname());
        person.setAge(personDto.getAge());

        return person;
    }

    public void addInitPersonToDb() {
        for (int i = 0; i < 10; i++) {
            Faker faker = new Faker(new Random(i));

            Person person = new Person();
            person.setFirstname(faker.name().firstName());
            person.setLastname(faker.name().lastName());
            person.setAge(new Random().nextInt(18, 80));

            personRepository.saveAndFlush(person);
        }
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
