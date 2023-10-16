package com.github.davimc.picpay.DTO;

import com.github.davimc.picpay.entities.Wallet;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record WalletNewDTO(
        @NotNull(message = "Person is required")
        Long personId,
        @PositiveOrZero(message = "Amount cant be negative")
        Double amount) {
}
