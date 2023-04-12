package com.project.dto;

import com.project.model.Permission;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDto {
    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    public Permission toPermission() {
        return new Permission();
    }

    public static PermissionDto from(Permission permission){
        return PermissionDto.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }
}
