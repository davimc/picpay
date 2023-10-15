package com.github.davimc.picpay.DTO;

import java.util.List;

public record RegisterDTO(String login, String password, Long personId, List<String> roles) {
}
