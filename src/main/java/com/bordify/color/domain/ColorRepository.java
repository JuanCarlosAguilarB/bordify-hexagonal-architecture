package com.bordify.color.domain;

import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;

import java.util.Optional;

/**
 * Repository interface for accessing and managing colorEntity entities in the database.
 */
public interface ColorRepository {

    public PageResult<Color> findAll(PaginationRequest  paginationRequest);

    public Optional<Color> findById(int id);

}
