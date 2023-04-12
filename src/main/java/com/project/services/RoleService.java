package com.project.services;

import com.project.dto.PermissionDto;
import com.project.dto.RoleDto;
import com.project.exceptions.RoleNotFoundException;
import com.project.model.Role;
import com.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(RoleDto roleDto) {

        Role role = Role.builder()
                .name(roleDto.getName())
                .permissions(roleDto.getPermissions().stream().map(PermissionDto::toPermission).collect(Collectors.toSet()))
                .build();
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found by id " + id));
        role.setName(roleDto.getName());
        role.setPermissions(roleDto.getPermissions().stream().map(PermissionDto::toPermission).collect(Collectors.toSet()));
        return roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found by id " + id));
        roleRepository.delete(role);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found by id " + id));
    }

    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(RoleDto::from).collect(Collectors.toList());
    }

    public boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }
}
