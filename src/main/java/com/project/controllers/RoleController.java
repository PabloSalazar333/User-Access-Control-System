package com.project.controllers;

import com.project.dto.RoleDto;
import com.project.model.Role;
import com.project.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody @Valid RoleDto roleDto){
        RoleDto createdRole = RoleDto.from(roleService.createRole(roleDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdRole.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @RequestBody @Valid RoleDto roleDto){
        Role updatedRole = roleService.updateRole(id, roleDto);
        return ResponseEntity.ok(RoleDto.from(updatedRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id){
        RoleDto role = RoleDto.from(roleService.getRoleById(id));
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id){
        Boolean exists = roleService.existsById(id);
        return ResponseEntity.ok(exists);
    }
}
