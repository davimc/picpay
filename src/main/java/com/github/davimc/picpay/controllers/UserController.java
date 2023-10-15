package com.github.davimc.picpay.controllers;

import com.github.davimc.picpay.DTO.UserDTO;
import com.github.davimc.picpay.DTO.UserNewDTO;
import com.github.davimc.picpay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findAll(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
