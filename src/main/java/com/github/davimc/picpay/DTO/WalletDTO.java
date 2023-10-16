package com.github.davimc.picpay.DTO;

import com.github.davimc.picpay.entities.Wallet;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class WalletDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Double amount;

    public WalletDTO() {
    }

    public WalletDTO(Wallet obj) {
        this.id = obj.getId();
        this.name = obj.getPerson().getName();
        this.amount = obj.getAmount();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletDTO walletDTO = (WalletDTO) o;
        return id.equals(walletDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
