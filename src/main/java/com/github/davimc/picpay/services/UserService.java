package com.github.davimc.picpay.services;

import com.github.davimc.picpay.DTO.UserDTO;
import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.repositories.UserRepository;
import com.github.davimc.picpay.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> obj = repository.findAll(pageable);
        Page<UserDTO> dto = obj.map(UserDTO::new);
        return dto;
    }

    public UserDTO findByEmail(String email) {
        User obj = repository.findByEmail(email);

        return new UserDTO(obj);
    }
    protected User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, User.class));
    }
    public UserDTO findByIdDto(Long id) {
        User obj = findById(id);

        return new UserDTO(obj);
    }
}
