package com.isaguler.dataexportdemo.repository;

import com.isaguler.dataexportdemo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}