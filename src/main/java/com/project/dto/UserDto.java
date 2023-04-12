package com.project.dto;

import com.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Set<RoleDto> roles;

    public static UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(RoleDto::from).collect(Collectors.toSet()))
                .build();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .username(username)
                .email(email)
                .password(null) // We don't want to expose the password in the DTO
                .roles(roles.stream().map(RoleDto::toRole).collect(Collectors.toSet()))
                .build();
    }
}
