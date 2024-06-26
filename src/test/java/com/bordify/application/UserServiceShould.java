package com.bordify.application;





import com.bordify.users.application.create.UserService;
import com.bordify.users.domain.UserNotFoundException;
import com.bordify.users.domain.User;
import com.bordify.users.domain.UserRepository;
import com.bordify.persistence.models.UserModelTestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceShould {

    private final UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
    private final UserService userService = new UserService(
            userRepositoryMock
    );


    @Test
    public void shouldFindUserByUsername() {

        // Given
        String userName = "XXXX";
        User userTest = UserModelTestService.createValidUser();
        userTest.setUsername(userName);

        // When
        when(userRepositoryMock.findByUsername(userName)).thenReturn(Optional.of(userTest));
        User user = userService.getUserByUsername(userName);

        // Then
        Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
        assertEquals(userName, user.getUsername());
        assertEquals(userTest.getId(), user.getId());
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenUserNotFound() {

        String userName = "XXXX";
        when(userRepositoryMock.findByUsername(userName)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByUsername(userName);
        });

    }

        @Test
    public void shouldCreateUser() {
        // the absence of errors is what tells me that the user was created correctly
        User userTest = UserModelTestService.createValidUser();

        userService.createUser(userTest);
        Mockito.verify(userRepositoryMock, Mockito.times(1)).save(userTest);


    }


}
