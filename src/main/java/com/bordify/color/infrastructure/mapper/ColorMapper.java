package com.bordify.color.infrastructure.mapper;

import com.bordify.color.domain.Color;
import com.bordify.color.infrastructure.persistence.ColorEntity;


public class ColorMapper {

    public Color toDomain(ColorEntity entity) {
        return Color.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public ColorEntity toEntity(Color color) {
        return ColorEntity.builder()
                .id(color.getId())
                .name(color.getName())
                .build();
    }

}
