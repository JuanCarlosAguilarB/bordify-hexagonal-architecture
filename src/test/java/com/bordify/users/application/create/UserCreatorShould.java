package com.bordify.users.application.create;

import com.bordify.users.domain.SecurityService;
import com.bordify.users.domain.UserModelTestService;
import com.bordify.users.domain.User;
import com.bordify.users.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserCreatorShould {

    private final UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
    private final SecurityService securityServiceMock = Mockito.mock(SecurityService.class);

    private final UserCreator userCreator = new UserCreator(
            userRepositoryMock, securityServiceMock
    );


    @DisplayName("Create a valid user")
    @Test
    public void shouldCreateUser() {
        // the absence of errors is what tells me that the user was created correctly
        User userTest = UserModelTestService.createValidUserDomain();

        userCreator.createUser(userTest);
        Mockito.verify(userRepositoryMock, Mockito.times(1)).save(userTest);

    }

}
