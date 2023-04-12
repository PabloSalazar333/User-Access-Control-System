package com.project.services;

import com.project.exceptions.RoleNotFoundException;
import com.project.model.Role;
import com.project.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    private final RoleRepository roleRepository;

    public RolService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id){
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()){
            return roleOptional.get();
        } else {
            throw new RoleNotFoundException("Role not found by id " + id);
        }
    }

    public Role saveRole(Role role){
        return  roleRepository.save(role);
    }

    public void deleteRole(Long id){
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
        } else {
            throw new RoleNotFoundException("Role not found by id " + id);
        }
    }
}
