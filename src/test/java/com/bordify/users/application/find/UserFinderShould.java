package com.bordify.users.application.find;

import com.bordify.users.domain.User;
import com.bordify.users.domain.UserModelTestService;
import com.bordify.users.domain.UserNotFoundException;
import com.bordify.users.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.bordify.users.domain.UserModelTestService.createValidUserDomain;
import static com.bordify.users.domain.UserModelTestService.createValidUserEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserFinderShould {

    private final UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);

    private final UserFinder userFinder = new UserFinder(
            userRepositoryMock
    );


    @DisplayName("Find user by username")
    @Test
    public void shouldFindUserByUsername() {

        // Given
        String userName = "XXXX";
        User userTest = createValidUserDomain();
        userTest.setUsername(userName);

        // When
        when(userRepositoryMock.findByUsername(userName)).thenReturn(Optional.of(userTest));
        User user = userFinder.findUserByUsername(userName);

        // Then
        Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
        assertEquals(userName, user.getUsername());
        assertEquals(userTest.getId(), user.getId());
    }

    @DisplayName("Throw exception when user not found")
    @Test
    public void shouldThrowUserNotFoundExceptionWhenUserNotFound() {

        String userName = "XXXX";
        when(userRepositoryMock.findByUsername(userName)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userFinder.findUserByUsername(userName);
        });

    }



}
