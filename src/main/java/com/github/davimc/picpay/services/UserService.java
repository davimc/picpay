package com.github.davimc.picpay.services;

import com.github.davimc.picpay.DTO.RegisterDTO;
import com.github.davimc.picpay.DTO.UserDTO;
import com.github.davimc.picpay.entities.Person;
import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.entities.enums.NotifyChannelType;
import com.github.davimc.picpay.repositories.RoleRepository;
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

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final PersonService personService;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;

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
    @Transactional(readOnly = true)
    public Optional<User> findByLogin (String username) {
        return repository.findByEmail(username);
    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new ObjectNotFoundException(username, User.class));
    }


    public Page<UserDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(UserDTO::new);
    }

    public UserDTO insert(RegisterDTO dto) {
        if(findByLogin(dto.login()).isPresent()) throw new IllegalArgumentException(dto.login() + "already registered");
        String passwordEncrypted = encoder.encode(dto.password());
        Person person = personService.find(dto.personId());
        User user = new User(null, dto.login(), passwordEncrypted, person);
        user.getRoles().addAll(dto.roles().stream().map(roleRepository::findByAuthority).collect(Collectors.toSet()));
        user.getChannels().addAll(dto.channels().stream().map(NotifyChannelType::toEnum).toList());
        user = repository.save(user);

        return new UserDTO(user);
    }
}
