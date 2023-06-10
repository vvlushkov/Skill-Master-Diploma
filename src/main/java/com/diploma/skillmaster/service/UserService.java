package com.diploma.skillmaster.service;

import com.diploma.skillmaster.dto.UserDto;
import com.diploma.skillmaster.model.Role;
import com.diploma.skillmaster.model.UserEntity;
import com.diploma.skillmaster.repository.RoleRepository;
import com.diploma.skillmaster.repository.UserRepository;
import com.diploma.skillmaster.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Method to save new user. It set role "USER" by default.
     * It is necessary that the controller sends a DTO with a certain list of roles.
     *
     * @param userDto - DTO object of UserEntity
     */
    public void save(UserDto userDto) {
        UserEntity user = UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword())) //THERE WILL BE PASSWORD ENCODER
                .roles(userDto.getRoles())
                .build();
        userRepository.save(user);
    }

    public boolean existsByEmailOrUsername(String email, String username) {
        return userRepository.existsByEmailOrUsername(email, username);
    }

    public UserEntity findByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new NoSuchElementException();
        }
        return user.get();
    }

    public void addCreatorRole() {
        Optional<String> username = SecurityUtil.getSessionUser();
        if (username.isPresent()) {
            UserEntity user = findByUsername(username.get());
            user.getRoles().add(roleRepository.findByName("CREATOR").orElseThrow());
            userRepository.save(user);
        }
    }
}
