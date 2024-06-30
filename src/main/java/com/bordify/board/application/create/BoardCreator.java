package com.bordify.board.application.create;

import com.bordify.board.domain.Board;
import com.bordify.board.domain.BoardRepository;
import com.bordify.shared.domain.ResourceNotCreatedException;
import org.springframework.stereotype.Service;

@Service
public class BoardCreator {

    private final BoardRepository boardRepository;

    public BoardCreator(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * Creates a new boardEntity and saves it in the database.
     *
     * @param board The boardEntity entity to be saved.
     * @throws ResourceNotCreatedException if the boardEntity cannot be created.
     */
    public void createBoard(Board board) {
        try {
            boardRepository.save(board);
        } catch (Exception e) {
            throw new ResourceNotCreatedException("Error creating boardEntity");
        }
    }
}
