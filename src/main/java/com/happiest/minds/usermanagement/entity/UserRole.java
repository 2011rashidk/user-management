package com.happiest.minds.usermanagement.entity;

import com.happiest.minds.usermanagement.id.UserRoleId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Table(name = "user_role")
@IdClass(UserRoleId.class)
@ToString
public class UserRole {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;
}
