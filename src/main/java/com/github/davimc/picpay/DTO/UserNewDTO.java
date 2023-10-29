package com.github.davimc.picpay.DTO;

import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.entities.enums.NotifyChannelType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserNewDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Profile is required")
    private Long personId;
    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "Password is required")
    private String password;

    @NotEmpty(message = "Role is required")
    private List<Long> roles = new ArrayList<>();

    private List<Integer> channels = new ArrayList<>();
    public UserNewDTO() {
    }

    public UserNewDTO(Long personId, String email, String password, List<Long> roles, List<Integer> channels) {
        this.personId = personId;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.channels = channels;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public List<Integer> getChannels() {
        return channels;
    }

    public User toEntity() {
        User user =  new User(null, email, password, null);
        user.getChannels().addAll(channels.stream().map(NotifyChannelType::toEnum).toList());

        return user;
    }
}
