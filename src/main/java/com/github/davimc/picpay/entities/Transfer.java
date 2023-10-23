package com.github.davimc.picpay.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_transfer")
public class Transfer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Wallet sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Wallet receiver;
     

    public Transfer() {
    }

    public Transfer(Long id, Double amount, Wallet sender, Wallet receiver) {
        this.id = id;
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Wallet getSender() {
        return sender;
    }

    public void setSender(Wallet sender) {
        this.sender = sender;
    }

    public Wallet getReceiver() {
        return receiver;
    }

    public void setReceiver(Wallet receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return id.equals(transfer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
