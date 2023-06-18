package com.diploma.skillmaster.dto;

import com.diploma.skillmaster.model.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    @Size(min = 4, max = 10, message = "Нікнейм повинен бути завдовжки від 4 до 10 символів")
    private String username;
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    @Email(message = "Електронна пошта повинна відповідати шаблону \"example@mail.com\"!")
    private String email;
    @NotEmpty(message = "Це поле повинно бути заповненим!")
    @Size(min = 4, max = 20, message = "Пароль повинен бути завдовжки від 8 до 20 символів")
    private String password;
    private List<Role> roles;
}
