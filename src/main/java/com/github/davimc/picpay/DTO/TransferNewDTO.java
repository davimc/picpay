package com.github.davimc.picpay.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransferNewDTO(
        @NotNull(message = "Receiver is required")
        Long receiver,
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must to be positive value")
        Double amount) {
}
