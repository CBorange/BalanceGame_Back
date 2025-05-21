package com.saks.balance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.saks.balance.backend.entity.GameTopic;

@Repository
public interface GameTopicRepository extends JpaRepository<GameTopic, Integer>{
    
}
