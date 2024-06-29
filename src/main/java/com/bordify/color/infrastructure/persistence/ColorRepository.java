package com.bordify.color.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing color entities in the database.
 */
public interface ColorRepository extends JpaRepository<Color, Integer> {
}
