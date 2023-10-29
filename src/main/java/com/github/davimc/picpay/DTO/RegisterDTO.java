package com.github.davimc.picpay.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterDTO(
        @NotNull(message = "Login is required")
        String login,
        @NotNull(message = "Password is required")
        String password,
        @NotNull(message = "person is required")
        Long personId,
        @NotNull(message = "Perfil is required")
        List<String> roles,
        List<Integer> channels){
}
