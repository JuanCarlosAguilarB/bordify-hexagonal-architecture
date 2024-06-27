package com.bordify.users.infrastructure.mapper;

import com.bordify.users.domain.User;
import com.bordify.users.infrastructure.persistence.UserEntity;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public static User toDomain(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .isVerified(userEntity.getIsVerified())
                .phoneNumber(userEntity.getPhoneNumber())
                .created(userEntity.getCreated())
                .lastLogin(userEntity.getLastLogin())
                .build();
    }


    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isVerified(user.getIsVerified())
                .phoneNumber(user.getPhoneNumber())
                .created(user.getCreated())
                .lastLogin(user.getLastLogin())
                .build();
    }

}
