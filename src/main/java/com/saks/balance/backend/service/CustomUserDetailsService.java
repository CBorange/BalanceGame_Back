package com.saks.balance.backend.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saks.balance.backend.entity.QUser;
import com.saks.balance.backend.entity.User;
import com.saks.balance.backend.model.CustomUserDetails;
import com.saks.balance.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User foundUser = userRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("%s 아이디에 해당하는 유저를 찾을 수 없음", id)));

        return new CustomUserDetails(foundUser);
    }

}
