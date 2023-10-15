package com.github.davimc.picpay.controllers;

import com.github.davimc.picpay.DTO.AuthenticationDTO;
import com.github.davimc.picpay.DTO.RegisterDTO;
import com.github.davimc.picpay.DTO.UserDTO;
import com.github.davimc.picpay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserService service;

    /*@GetMapping
    public*/
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO newDTO) {
        UserDTO dto = service.insert(newDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);

    }
}
