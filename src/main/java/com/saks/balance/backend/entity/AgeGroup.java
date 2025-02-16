package com.saks.balance.backend.entity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity(name = "age_group")
@Getter
public class AgeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
