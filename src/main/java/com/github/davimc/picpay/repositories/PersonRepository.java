package com.github.davimc.picpay.repositories;

import com.github.davimc.picpay.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
