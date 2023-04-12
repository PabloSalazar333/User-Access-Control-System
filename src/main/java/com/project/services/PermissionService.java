package com.project.services;

import com.project.exceptions.PermissionNotFoundException;
import com.project.model.Permission;
import com.project.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public Permission createPermission(String  name){
        if(permissionRepository.existsByName(name)){
            throw new PermissionNotFoundException("Permission with name " + name + "already exists");
        }
        Permission permission = new Permission();
        return permissionRepository.save(permission);
    }

    public Permission getPermissionById(Long id){
        return permissionRepository.findById(id).orElseThrow(() -> new PermissionNotFoundException("Permission not found by id " + id));
    }

    public Permission getPermissionByName(String name){
        return permissionRepository.findByName(name).orElseThrow(()-> new PermissionNotFoundException("Permission not found by name " + name));
    }

    public List<Permission> getAllPermissions(){
        return permissionRepository.findAll();
    }

    public void deletePermission(Long id){
        if (!permissionRepository.existsById(id)) {
            throw new PermissionNotFoundException("Permission not found by id " + id);
        }
        permissionRepository.deleteById(id);
    }

    public Permission updatePermission(Long id, Permission updatePermission){
        if (!permissionRepository.existsById(id)){
            throw new PermissionNotFoundException("Permission not found by id " + id);
        }
        updatePermission.setId(id);
        return permissionRepository.save(updatePermission);
    }
}
