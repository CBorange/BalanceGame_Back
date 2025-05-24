package com.saks.balance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.saks.balance.backend.entity.GameChoice;

@Repository
public interface GameChoiceRepository extends JpaRepository<GameChoice, Integer>{
    
}
