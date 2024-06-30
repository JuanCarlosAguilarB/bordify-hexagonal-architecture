package com.bordify.board.application.update;

import com.bordify.board.domain.Board;
import com.bordify.board.domain.BoardRepository;
import com.bordify.shared.domain.EntityNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BoardUpdater {

    private final BoardRepository boardRepository;

    /**
     * Updates an existing board.
     *
     * @param boardId The ID of the board to update.
     * @param board The updated board entity.
     * @throws EntityNotFound if the board to update does not exist.
     */
    public void update(UUID boardId, Map<String,Object> board) {

        Optional<Board> existingBoard = boardRepository.findById(boardId);

        if (existingBoard.isEmpty()) {
            throw new EntityNotFound("Board with id " + boardId + " does not exist.");
        }

            board.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Board.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingBoard.get(), value);
            });

            boardRepository.save(existingBoard.get());

        }


}
