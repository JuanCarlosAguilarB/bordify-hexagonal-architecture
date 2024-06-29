package com.bordify.topic.infrastructure.persistence;

import com.bordify.shared.domain.PaginationRequest;
import com.bordify.topic.domain.TopicListDTO;
import com.bordify.topic.domain.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class TopicJpaAdapter implements TopicRepository {

    private final TopicJpaRepository topicJpaRepository;

    @Override
    public List<TopicListDTO> findByBoardIdCustom(UUID boardId, PaginationRequest pageable) {
        return List.of();
    }
}
