package com.github.davimc.picpay.controllers;

import com.github.davimc.picpay.DTO.NotificationDTO;
import com.github.davimc.picpay.DTO.PersonDTO;
import com.github.davimc.picpay.DTO.PersonNewDTO;
import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.services.NotificationService;
import com.github.davimc.picpay.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:5173/")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<NotificationDTO>> findAllByUser(@AuthenticationPrincipal User user, Pageable pageable ) {
        return ResponseEntity.ok().body(service.findByUser(pageable, user));
    }
}
