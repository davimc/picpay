package com.github.davimc.picpay.controllers;

import com.github.davimc.picpay.DTO.WalletDTO;
import com.github.davimc.picpay.DTO.WalletNewDTO;
import com.github.davimc.picpay.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<WalletDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<WalletDTO> findAll(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findByID(id));
    }

    @PostMapping
    public ResponseEntity<WalletDTO> create(@RequestBody @Valid WalletNewDTO newDto) {
        WalletDTO dto = service.create(newDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
