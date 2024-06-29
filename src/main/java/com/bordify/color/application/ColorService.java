package com.bordify.color.application;

import com.bordify.color.infrastructure.persistence.Color;
import com.bordify.color.infrastructure.persistence.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing color operations.
 * This service provides functionalities for creating new colors and listing all colors with pagination support.
 */
@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    /**
     * Saves a new color entity in the database.
     *
     * @param color The color entity to be persisted.
     */
    public void createColor(Color color) {
        colorRepository.save(color);
    }

    /**
     * Retrieves all colors in a paginated format.
     *
     * @param pageable The pagination information.
     * @return A page of {@link Color} entities.
     */
    public Page<Color> listColors(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }
}