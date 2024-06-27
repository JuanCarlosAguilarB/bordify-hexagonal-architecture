package com.bordify.persistence.models;

import com.bordify.board.infrastructure.persistence.BoardEntity;

import com.bordify.models.User;

import java.util.UUID;

public class BoardModelTestService  {

    public static BoardEntity createValidBoard(User user) {

//        User user = UserModelTestService.createValidUser();
        UUID userId = user.getId();

        return BoardEntity.builder()
                .id(UUID.randomUUID())
                .name("Test BoardEntity")
                .user(user)
                .userId(userId)
                .build();
    }

}
