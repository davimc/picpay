package com.github.davimc.picpay.DTO;

import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.entities.enums.NotifyChannelType;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private List<String> channels = new ArrayList<>();

    public UserDTO(User user) {
        id = user.getId();
        name = user.getPerson().getName();
        email = user.getEmail();
        channels = user.getChannels().stream().map(NotifyChannelType::getQualifier).toList();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getChannels() {
        return channels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id.equals(userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
