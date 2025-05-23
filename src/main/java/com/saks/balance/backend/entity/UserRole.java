package com.saks.balance.backend.entity;

import com.saks.balance.backend.entity.serializable.UserRoleId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user_role")
@Getter
public class UserRole {
    @EmbeddedId
    @Setter
    private UserRoleId id;

    // 아래 매핑 객체들은 Select 전용, Insert, Update 시에는 위에 EmbeddedId 사용해서 처리(혼동 방지)

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;
}

