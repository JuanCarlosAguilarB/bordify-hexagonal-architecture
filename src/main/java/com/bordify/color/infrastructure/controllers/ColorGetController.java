package com.bordify.color.infrastructure.controllers;

import com.bordify.color.application.find.ColorFinder;
import com.bordify.color.domain.Color;
import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for managing colorEntity catalog operations.
 */
@Tag(name = "Catalogs", description = "Catalogs management operations")
@RestController
@AllArgsConstructor
public class ColorGetController {



    private final ColorFinder colorFinder;


    /**
     * Retrieves a list of all colors.
     *
     * @param pageable Pagination information for the list.
     * @return ResponseEntity containing the list of colors.
     */
    @Operation(summary = "List colors", description = "List all colors", tags = { "Catalogs" })
    @GetMapping("/v1/catalogs/colors/")
    public ResponseEntity<?> listColors(Pageable pageable) {

        PaginationRequest  paginationRequest = new PaginationRequest(pageable.getPageNumber(), pageable.getPageSize());

        PageResult<Color> colors = colorFinder.listColors(paginationRequest);
        return ResponseEntity.ok(colors);

    }

}