package com.saks.balance.backend.entity;

import com.saks.balance.states.GlobalStates;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "user")
@Getter
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "account_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private GlobalStates.AccoutState accountState;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "sns_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GlobalStates.SnsType snsType;

    @Column(name = "age", nullable = false)
    private int age;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;
}

