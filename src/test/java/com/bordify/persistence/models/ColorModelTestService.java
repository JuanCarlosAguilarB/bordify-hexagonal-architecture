package com.bordify.persistence.models;

import com.bordify.color.infrastructure.persistence.ColorEntity;

public class ColorModelTestService {

    public static ColorEntity createValidColor() {

        int colorId = (int) (Math.random() * 1000);
        ColorEntity colorEntity = ColorEntity.builder()
                .id(colorId)
                .name("Red")
                .hex("#FF0000")
                .build();
        return colorEntity;
    }

}
