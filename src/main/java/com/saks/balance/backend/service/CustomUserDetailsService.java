package com.saks.balance.backend.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saks.balance.backend.entity.QUser;
import com.saks.balance.backend.entity.User;
import com.saks.balance.backend.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final JPAQueryFactory queryFactory;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User foundUser = queryFactory.selectFrom(QUser.user)
                .where(QUser.user.id.eq(userId)).fetchOne();
        if(foundUser == null){
            throw new UsernameNotFoundException(String.format("%s 아이디에 해당하는 유저를 찾을 수 없음", userId));
        }

        return new CustomUserDetails(foundUser);
    }
}
