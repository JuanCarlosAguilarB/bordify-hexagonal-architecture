package com.bordify.boards.application.create;

import com.bordify.board.application.create.BoardCreator;
import com.bordify.board.domain.Board;
import com.bordify.board.domain.BoardRepository;
import com.bordify.users.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.bordify.boards.infrastructure.persistence.models.BoardModelTestService.createValidBoardDomain;
import static com.bordify.users.domain.UserModelTestService.createValidUserDomain;

public class BoardCreatorShould {

    private final BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private final BoardCreator boardCreator = new BoardCreator(boardRepository);
    /*
     * This test case verifies that a board can be created with a valid name.
     * It creates a mock repository and uses the BoardCreator class to create a board with the name "My Board".
     * It then asserts that the created board has the expected name.
     */
    @DisplayName("Create a valid board")
    @Test
    public void should_create_a_board() {

        User  user = createValidUserDomain();
        Board board = createValidBoardDomain(user);
        boardCreator.createBoard(board);

        Mockito.verify(boardRepository, Mockito.times(1)).save(board);

//        var mockRepository = new MockBoardRepository();
//        var boardCreator = new BoardCreator(mockRepository);
//        var createBoardCommand = new CreateBoardCommand("My Board");
//        var board = boardCreator.create(createBoardCommand);
//
//        assertEquals("My Board", board.getName());



    }

}
