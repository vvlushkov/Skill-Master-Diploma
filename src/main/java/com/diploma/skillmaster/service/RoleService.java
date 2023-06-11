package com.diploma.skillmaster.service;

import com.diploma.skillmaster.dto.UserDto;
import com.diploma.skillmaster.mapper.UserMapper;
import com.diploma.skillmaster.model.Role;
import com.diploma.skillmaster.model.UserEntity;
import com.diploma.skillmaster.repository.RoleRepository;
import com.diploma.skillmaster.repository.UserRepository;
import com.diploma.skillmaster.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public void addCreatorRole() {
        Optional<String> username = SecurityUtil.getSessionUser();
        if (username.isPresent()) {
            UserEntity user = UserMapper.mapToUser(userService.findByUsername(username.get()));
            user.getRoles().add(roleRepository.findByName("CREATOR").orElseThrow());
            userRepository.save(user);
        }
    }

    public void addUserRoleByDefaultAndSave(UserDto userDto) {
        Optional<Role> userRole = roleRepository.findByName("USER");
        userDto.setRoles(List.of(userRole.orElseThrow()));
        userService.save(userDto);
    }
}
