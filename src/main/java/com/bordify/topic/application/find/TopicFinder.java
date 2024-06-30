package com.bordify.topic.application.find;

import com.bordify.task.application.TaskService;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.topic.domain.TopicListDTO;
import com.bordify.topic.domain.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TopicFinder {

    private final TopicRepository  topicRepository;
    private final TaskService taskService;

    /**
     * Retrieves a paginated list of topicEntities for a specified board with their related tasks.
     *
     * @param boardId The UUID of the board.
     * @param pageable The pagination information.
     * @return A page of {@link TopicListDTO} each populated with related tasks.
     */
    public List<TopicListDTO> getTopicsOfBoard(UUID boardId, PaginationRequest pageable) {
        List<TopicListDTO> topics = topicRepository.findByBoardIdCustom(boardId, pageable);

        int pageNumber = 0;
        int pageSize = 5;

        Pageable pageableTask = PageRequest.of(pageNumber, pageSize);

//        for (TopicListDTO topic : topics) {
//            topic.setTasks(taskService.getTaskForBoard(topic.getId(), pageableTask));
//        }

        return topics;
    }

}
