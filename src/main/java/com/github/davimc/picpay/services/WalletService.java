package com.github.davimc.picpay.services;

import com.github.davimc.picpay.DTO.WalletDTO;
import com.github.davimc.picpay.DTO.WalletNewDTO;
import com.github.davimc.picpay.entities.Person;
import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.entities.Wallet;
import com.github.davimc.picpay.repositories.WalletRepository;
import com.github.davimc.picpay.services.exceptions.InsufficientFundsException;
import com.github.davimc.picpay.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {
    private final WalletRepository repository;
    private final UserService userService;

    public WalletService(WalletRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public Page<WalletDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(WalletDTO::new);
    }
    @Transactional(readOnly = true)
    protected Wallet find(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Wallet.class));

    }
    @Transactional(readOnly = true)
    protected Wallet findByUser(User user) {
        return repository.findByUser(user);
    }

    @Transactional(readOnly = true)
    public WalletDTO findByUserDTO(User user) {
        return new WalletDTO(findByUser(user));
    }
    @Transactional(readOnly = true)
    public WalletDTO findByID(Long id) {
        return new WalletDTO(find(id));
    }

    @Transactional
    public WalletDTO create(WalletNewDTO dto) {
        User x = userService.find(dto.userId());
        Wallet obj = new Wallet(null, dto.amount(), x);
        obj = repository.save(obj);

        return new WalletDTO(obj);
    }

    public void transfer(Wallet w1, Wallet w2, Double amount) {
        // Valor maior que 0 não é tratado aqui, pois é tratado no validation no dto
        if (w1.getAmount() <= amount)
            throw new InsufficientFundsException();
        w1.setAmount(w1.getAmount()-amount);
        w2.setAmount(w2.getAmount()+amount);
        repository.save(w1);
        repository.save(w2);
    }
}
