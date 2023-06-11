package com.diploma.skillmaster.mapper;

import com.diploma.skillmaster.dto.UserDto;
import com.diploma.skillmaster.model.UserEntity;

public interface UserMapper {
    static UserEntity mapToUser(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .roles(userDto.getRoles())
                .build();
    }

    static UserDto mapToUserDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}
