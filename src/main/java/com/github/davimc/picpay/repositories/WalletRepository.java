package com.github.davimc.picpay.repositories;

import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUser(User user);
}
