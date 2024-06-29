package com.bordify.color.infrastructure.persistence;

import com.bordify.color.domain.Color;
import com.bordify.color.domain.ColorRepository;
import com.bordify.color.infrastructure.mapper.ColorMapper;
import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ColorJpaRepositoryAdapter implements ColorRepository {

    private final ColorJpaRepository colorJpaRepository;
    private final ColorMapper colorMapper = new ColorMapper();

    @Override
    public PageResult<Color> findAll(PaginationRequest paginationRequest) {

        Pageable pageableResult = PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize()); // page 0, size 20

        Page<ColorEntity> page = colorJpaRepository.findAll(pageableResult);

        List<Color> colors =  page.getContent().stream().map(colorMapper::toDomain).toList();

        return new PageResult<Color>(colors,page.getNumber(), page.getSize(), page.getTotalElements());

    }

    @Override
    public Optional<Color> findById(int id) {

        Optional<ColorEntity> colorEntity = colorJpaRepository.findById(id);
        return colorEntity.map(colorMapper::toDomain);
    }
}
