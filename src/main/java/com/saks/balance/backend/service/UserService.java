package com.saks.balance.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saks.balance.backend.dto.SignupRequest;
import com.saks.balance.backend.entity.Role;
import com.saks.balance.backend.entity.User;
import com.saks.balance.backend.entity.UserRole;
import com.saks.balance.backend.entity.serializable.UserRoleId;
import com.saks.balance.backend.repository.RoleRepository;
import com.saks.balance.backend.repository.UserRepository;
import com.saks.balance.backend.repository.UserRoleRepository;
import com.saks.balance.states.GlobalStates;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUser(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public boolean validatePassword(String userId, String rawPassword){
        User user = userRepository.getReferenceById(userId);
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    @Transactional
    public User signUpUser(SignupRequest request){
        User user = User.builder()
            .id(request.getId())
            .password(passwordEncoder.encode(request.getPassword()))
            .username(request.getUsername())
            .age(request.getAge())
            .phoneNumber(request.getPhoneNumber())
            .email(request.getEmail())
            .introduce(request.getIntroduce())
            .accountState(GlobalStates.AccoutState.Active)
            .snsType(request.getSnsType())
            .createDate(LocalDateTime.now())
            .cash(BigDecimal.ZERO)
            .build();

        userRepository.save(user);

        Role defaultUserRole = roleRepository.findByAuthority(GlobalStates.Authority.User);
        UserRole userRole = new UserRole();
        userRole.setId(
            new UserRoleId(user.getId(), defaultUserRole.getId())
        );
        userRoleRepository.save(userRole);

        return user;
    }
}
