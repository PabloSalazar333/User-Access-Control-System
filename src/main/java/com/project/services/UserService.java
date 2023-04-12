package com.project.services;

import com.project.dto.UserDto;
import com.project.exceptions.RoleNotFoundException;
import com.project.exceptions.UserAlreadyExistsException;
import com.project.exceptions.UserNotFoundException;
import com.project.model.Role;
import com.project.model.User;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByUserName(userDto.getUsername())) {
            throw new UserAlreadyExistsException("User with username " + userDto.getUsername() + " already exists");
        }

        String encodePassword = passwordEncoder.encode(userDto.toUser().getPassword());
        User user = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(encodePassword)
                .roles(new HashSet<>())
                .build();
        User savedUser = userRepository.save(user);

        return UserDto.from(savedUser);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found by id " + id));

        if (!user.getUsername().equals(userDto.getUsername()) && userRepository.existsByUserName(userDto.getUsername())) {
            throw new UserAlreadyExistsException("User with username " + userDto.getUsername() + " already exists");
        }

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        if (userDto.toUser().getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.toUser().getPassword()));
        }

        User updatedUser = userRepository.save(user);

        return UserDto.from(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id " + id));
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }

    public User addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found by id " + userId));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role not found bi id " + roleId));
        user.getRoles().add(role);
        userRepository.save(user);
        return user;
    }
}
