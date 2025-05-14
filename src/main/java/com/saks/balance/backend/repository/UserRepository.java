package com.saks.balance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saks.balance.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    
}
