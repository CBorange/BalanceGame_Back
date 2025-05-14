package com.saks.balance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saks.balance.backend.entity.UserRole;
import com.saks.balance.backend.entity.serializable.UserRoleId;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId>{
    
}
