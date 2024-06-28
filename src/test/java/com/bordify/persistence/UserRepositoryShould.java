package com.bordify.persistence;
import com.bordify.users.domain.User;
import com.bordify.users.infrastructure.persistence.UserEntity;
import com.bordify.users.infrastructure.persistence.UserJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.bordify.users.domain.UserModelTestService.createValidUserEntity;

@DataJpaTest
public class UserRepositoryShould {

    @Autowired
    private UserJpaRepository userRepository;

    @DisplayName("Should find user by email")
    @Test
    public void shouldFindUserByEmail() {
        UserEntity userTest = createValidUserEntity();
        userRepository.save(userTest);

        boolean hasUser = userRepository.existsByEmail(userTest.getEmail());

        Assertions.assertTrue(hasUser);
    }

    @DisplayName("Should find user by userName")
    @Test
    public void shouldFindUserByUsername() {
        UserEntity userTest = createValidUserEntity();
        userRepository.save(userTest);

        boolean hasUser = userRepository.existsByUsername(userTest.getUsername());

        Assertions.assertTrue(hasUser);
    }

    @DisplayName("Should get an user by userName")
    @Test
    public void shouldGetAnUserByUsername() {
        UserEntity userTest = createValidUserEntity();
        userRepository.save(userTest);

        Optional<UserEntity> user = userRepository.findByUsername(userTest.getUsername());

        Assertions.assertEquals(userTest, user);
    }


    @DisplayName("Should get an user by email")
    @Test
    public void shouldGetAnUserByEmail() {
        UserEntity userTest = createValidUserEntity();
        userRepository.save(userTest);

        Optional<UserEntity> user = userRepository.findByEmail(userTest.getEmail());

        Assertions.assertEquals(userTest, user.isPresent() ? user.get() : null);
    }


//    public User createValidEntity() {
//        return User.builder()
//                .id(UUID.randomUUID())
//                .firstName("John")
//                .lastName("Doe")
//                .email("john.doe@example.com")
//                .build();
//    }
}