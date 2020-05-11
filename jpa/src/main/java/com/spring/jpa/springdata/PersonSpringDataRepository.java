package com.spring.jpa.springdata;

import com.spring.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonSpringDataRepository extends JpaRepository<Person, Integer> { }
