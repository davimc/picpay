package com.github.davimc.picpay.services;

import com.github.davimc.picpay.DTO.WalletDTO;
import com.github.davimc.picpay.DTO.WalletNewDTO;
import com.github.davimc.picpay.entities.Person;
import com.github.davimc.picpay.entities.Wallet;
import com.github.davimc.picpay.repositories.WalletRepository;
import com.github.davimc.picpay.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {
    private final WalletRepository repository;
    private final PersonService personService;

    public WalletService(WalletRepository repository, PersonService personService) {
        this.repository = repository;
        this.personService = personService;
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
    public WalletDTO findByID(Long id) {
        return new WalletDTO(find(id));
    }

    @Transactional
    public WalletDTO create(WalletNewDTO dto) {
        Person p = personService.find(dto.person_id());
        Wallet obj = new Wallet(null, dto.amount(), p);
        obj = repository.save(obj);

        return new WalletDTO(obj);
    }
}
