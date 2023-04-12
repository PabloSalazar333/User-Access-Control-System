package com.project.dto;

import com.project.model.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    private Set<PermissionDto> permissions;

    public Role toRole(){
        return new Role();
    }


    public static RoleDto from(Role role){
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .permissions(role.getPermissions().stream().map(PermissionDto::from).collect(Collectors.toSet()))
                .build();
    }
}
