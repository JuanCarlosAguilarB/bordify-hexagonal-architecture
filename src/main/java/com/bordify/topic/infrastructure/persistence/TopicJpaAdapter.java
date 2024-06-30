package com.bordify.topic.infrastructure.persistence;

import com.bordify.shared.domain.PaginationRequest;
import com.bordify.topic.domain.Topic;
import com.bordify.topic.domain.TopicListDTO;
import com.bordify.topic.domain.TopicRepository;
import com.bordify.topic.infrastructure.mapper.TopicMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bordify.topic.infrastructure.mapper.TopicMapper.toEntity;

@Repository
@AllArgsConstructor
public class TopicJpaAdapter implements TopicRepository {

    private final TopicJpaRepository topicJpaRepository;

    @Override
    public List<TopicListDTO> findByBoardIdCustom(UUID boardId, PaginationRequest pageable) {
        return List.of();
    }

    @Override
    public void save(Topic topic) {
        TopicEntity topicEntity = toEntity(topic);
        topicJpaRepository.save(topicEntity);
    }

    @Override
    public void deleteById(UUID id) {
        topicJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Topic> findById(UUID id) {
        Optional<TopicEntity> topicEntity = topicJpaRepository.findById(id);
        return topicEntity.map(TopicMapper::toDomain);
    }
}
