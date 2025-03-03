package com.saks.balance.backend.entity.serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
///
/// UserRole 테이블 다중키 모델
public class UserRoleId implements Serializable {
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    public UserRoleId(){}

    public UserRoleId(String userId, Integer roleId){
        this.userId = userId;
        this.roleId = roleId;
    }
}
