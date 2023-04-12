package com.project.dto;

import com.project.model.Permission;
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
    private String name;

    public static PermissionDto from(Permission permission){
        return PermissionDto.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }
}
