package com.saks.balance.backend.entity;
import com.saks.balance.states.GlobalStates;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private GlobalStates.Authority authority;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;
}

