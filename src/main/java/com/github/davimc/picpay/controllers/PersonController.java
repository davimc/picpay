package com.github.davimc.picpay.controllers;

import com.github.davimc.picpay.DTO.PersonDTO;
import com.github.davimc.picpay.DTO.PersonNewDTO;
import com.github.davimc.picpay.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<PersonDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findAll(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findByID(id));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody @Valid PersonNewDTO newDto) {
        PersonDTO dto = service.create(newDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
