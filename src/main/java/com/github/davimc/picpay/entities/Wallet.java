package com.github.davimc.picpay.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_wallet")
public class Wallet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "sender")
    private List<Transfer> sended = new ArrayList<>();
    @OneToMany(mappedBy = "receiver")
    private List<Transfer> received = new ArrayList<>();


    public Wallet() {
    }

    public Wallet(Long id, Double amount, User user) {
        this.id = id;
        this.amount = amount;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transfer> getSended() {
        return sended;
    }

    public List<Transfer> getReceived() {
        return received;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return id.equals(wallet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
