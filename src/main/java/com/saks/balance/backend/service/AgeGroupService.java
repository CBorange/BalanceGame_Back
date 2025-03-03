package com.saks.balance.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saks.balance.backend.entity.AgeGroup;
import com.saks.balance.backend.entity.QAgeGroup;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgeGroupService {
    private final JPAQueryFactory queryFactory;

    public List<AgeGroup> getAll(){
        List<AgeGroup> ageGroups = queryFactory
                            .selectFrom(QAgeGroup.ageGroup)
                            .fetch();
        return ageGroups;
    }
}
