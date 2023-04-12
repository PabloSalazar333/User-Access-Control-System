package com.project.dto;

import com.project.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    private Long id;
    private String name;
    private Set<PermissionDto> permissions;

    public Role toRole(){
        return new Role(id, name);
    }


    public static RoleDto from(Role role){
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .permissions(role.getPermissions().stream().map(PermissionDto::from).collect(Collectors.toSet()))
                .build();
    }
}
