package com.bordify.color.application.find;

import com.bordify.color.domain.Color;
import com.bordify.color.domain.ColorRepository;

import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for managing colorEntity operations.
 * This service provides functionalities for creating new colors and listing all colors with pagination support.
 */
@Service
@AllArgsConstructor
public class ColorFinder {


    private final ColorRepository colorRepository;
    /**
     * Retrieves all colors in a paginated format.
     *
     * @param pageable The pagination information.
     * @return A page of {@link Color} entities.
     */
    public PageResult<Color> listColors(PaginationRequest pageable) {

        return colorRepository.findAll(pageable);
    }


    public Color findColorById(int id) {
//        return colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Color not found"));

        return colorRepository.findById(id).orElse(null);
    }

}