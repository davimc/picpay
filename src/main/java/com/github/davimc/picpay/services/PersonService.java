package com.github.davimc.picpay.services;

import com.github.davimc.picpay.DTO.PersonDTO;
import com.github.davimc.picpay.DTO.PersonNewDTO;
import com.github.davimc.picpay.entities.Person;
import com.github.davimc.picpay.repositories.PersonRepository;
import com.github.davimc.picpay.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Page<PersonDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(PersonDTO::new);
    }

    protected Person find(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Person.class));

    }
    public PersonDTO findByID(Long id) {
        return new PersonDTO(find(id));
    }

    public PersonDTO create(PersonNewDTO dto) {
        Person obj = dto.toEntity();
        obj = repository.save(obj);

        return new PersonDTO(obj);
    }
}
