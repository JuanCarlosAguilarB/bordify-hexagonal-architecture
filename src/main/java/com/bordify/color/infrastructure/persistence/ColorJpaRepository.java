package com.bordify.color.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing colorEntity entities in the database.
 */
public interface ColorJpaRepository extends JpaRepository<ColorEntity, Integer> {
     public Page<ColorEntity> findAllBy(Pageable pageable);
}
