package com.project.controllers;

import com.project.dto.PermissionDto;
import com.project.services.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ResponseEntity<PermissionDto> createPermission(@RequestBody @Valid PermissionDto permissionDto){
        PermissionDto createdPermission = PermissionDto.from(permissionService.createPermission(String.valueOf(permissionDto)));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdPermission.getId()).toUri();
        return ResponseEntity.created(location).body(createdPermission);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> getPermissionsById(@PathVariable Long id){
        PermissionDto permissionDto = PermissionDto.from(permissionService.getPermissionById(id));
        return ResponseEntity.ok(permissionDto);
    }

    @GetMapping(params = "name")
    public ResponseEntity<PermissionDto> getPermissionByName(@RequestParam String name){
        PermissionDto permissionDto = PermissionDto.from(permissionService.getPermissionByName(name));
        return ResponseEntity.ok(permissionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id){
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDto> updatePermission(@PathVariable Long id, @RequestBody @Valid PermissionDto permissionDto){
        PermissionDto updatedPermission = PermissionDto.from(permissionService.updatePermission(id, permissionDto.toPermission()));
        return ResponseEntity.ok(updatedPermission);
    }
}
