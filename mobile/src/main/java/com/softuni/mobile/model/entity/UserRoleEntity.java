package com.softuni.mobile.model.entity;


import com.softuni.mobile.model.enums.Role;
import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "name")
    private Role role;

    public UserRoleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public UserRoleEntity setRole(Role role) {
        this.role = role;

        return this;
    }
}
