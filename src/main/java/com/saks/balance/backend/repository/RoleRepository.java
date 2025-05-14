package com.saks.balance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saks.balance.backend.entity.Role;
import com.saks.balance.states.GlobalStates;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByAuthority(GlobalStates.Authority authority);
}
