package dev.lommebok.lommebok.dto.user.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class LoginRequest {

    @NotEmpty(message = "This email is invalid")
    public String email;
    @NotEmpty(message = "This password is invalid")
    public String password;
}
