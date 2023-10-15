package com.github.davimc.picpay.DTO;

import com.github.davimc.picpay.entities.User;
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
    private List<Integer> roles = new ArrayList<>();

    public UserNewDTO() {
    }

    public UserNewDTO(Long personId, String email, String password, List<Integer> roles) {
        this.personId = personId;
        this.email = email;
        this.password = password;
        this.roles = roles;
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

    public List<Integer> getRoles() {
        return roles;
    }

    public User toEntity() {
        return new User(null, email, password, null);
    }
}
