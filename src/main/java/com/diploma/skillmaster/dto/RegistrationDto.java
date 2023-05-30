package com.diploma.skillmaster.dto;

import com.diploma.skillmaster.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    @Min(4)
    @Max(10)
    private String username;
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    @Email(message = "Електронна пошта повинна відповідати шаблону \"example@mail.com\"!")
    private String email;
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    @Min(8)
    @Max(16)
    private String password;
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    private Role role;
}
