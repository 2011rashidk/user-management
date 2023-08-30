package com.happiest.minds.usermanagement.entity;

import com.happiest.minds.usermanagement.id.RolePermissionId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Table(name = "role_permission")
@IdClass(RolePermissionId.class)
@ToString
public class RolePermission {

    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

}
