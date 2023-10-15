package com.github.davimc.picpay.services;

import com.github.davimc.picpay.DTO.UserDTO;
import com.github.davimc.picpay.DTO.UserNewDTO;
import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.repositories.UserRepository;
import com.github.davimc.picpay.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final PersonService personService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository, PersonService personService) {
        this.repository = repository;
        this.personService = personService;
    }
    @Transactional(readOnly = true)
    protected User find (Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, User.class));
    }
    @Transactional(readOnly = true)
    public UserDTO findById (Long id) {
        User user = find(id);
        return new UserDTO(user);
    }
    @Transactional
    public UserDTO insert (UserNewDTO dto) {
        User user = dto.toEntity();
        user = repository.save(user);
        user.setPerson(personService.find(dto.getPersonId()));

        return new UserDTO(user);
    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new ObjectNotFoundException(username, User.class));
    }


    public Page<UserDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(UserDTO::new);
    }
}
