package com.diploma.skillmaster.service;

import com.diploma.skillmaster.dto.UserDto;
import com.diploma.skillmaster.model.UserEntity;
import com.diploma.skillmaster.repository.RoleRepository;
import com.diploma.skillmaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Method to save new user. It set role "USER" by default.
     * It is necessary that the controller sends a DTO with a certain list of roles.
     *
     * @see UserEntity
     * @see UserRepository
     * @see RoleRepository
     * @param userDto - DTO object of UserEntity
     */
    public void saveUser(UserDto userDto) {
        UserEntity user = UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword()) //THERE WILL BE PASSWORD ENCODER
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
}
