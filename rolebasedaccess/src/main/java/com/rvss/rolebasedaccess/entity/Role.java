package com.rvss.rolebasedaccess.entity;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(unique = true, nullable = false)
    private String name; // e.g. ROLE_ADMIN, ROLE_SCHOOL_ADMIN, etc.
    public Role(String name) {
        this.name = name;
    }
}
