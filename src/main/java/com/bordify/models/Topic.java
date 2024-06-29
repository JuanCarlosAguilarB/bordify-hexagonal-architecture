package com.bordify.models;

import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.color.infrastructure.persistence.ColorEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Represents a topic entity in the Bordify application. A topic is associated with a board, a colorEntity,
 * and can contain multiple tasks.
 * This class uses Lombok annotations to simplify the boilerplate code for getter, setter, and constructors.
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Topic {

    /**
     * Unique identifier for the Topic. It is automatically generated and uses UUID as the ID type.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Name of the topic.
     */
    private String name;

    /**
     * The ID of the colorEntity associated with this topic. This field is used for database operations but is
     * not directly updatable or insertable through the Topic entity to prevent inconsistency.
     */
    @Column(name = "color_id")
    private Integer colorId;

    /**
     * The ID of the boardEntity associated with this topic. This field is used for database operations but is
     * not directly updatable or insertable through the Topic entity to prevent inconsistency.
     */
    @Column(name = "board_id")
    private UUID boardId;

    /**
     * The BoardEntity entity associated with this topic. Mapped using a many-to-one relationship where each topic
     * belongs to a single boardEntity. The @JoinColumn annotation specifies that this entity uses the 'board_id'
     * column in the Topic table to join to the BoardEntity table.
     */
    @ManyToOne
    @JoinColumn(name = "board_id", insertable = false, updatable = false)
    private BoardEntity boardEntity;

    /**
     * The ColorEntity entity associated with this topic. Mapped using a many-to-one relationship where each topic
     * is associated with a single colorEntity. The @JoinColumn annotation specifies that this entity uses the
     * 'color_id' column in the Topic table to join to the ColorEntity table.
     */
    @ManyToOne
    @JoinColumn(name = "color_id", insertable = false, updatable = false)
    private ColorEntity colorEntity;

    /**
     * List of tasks associated with this topic. Defined as a one-to-many relationship with cascade
     * type ALL, meaning all persistence operations (like save and delete) on a Topic will cascade to its
     * tasks.
     */
    @OneToMany(mappedBy = "topicId", cascade = CascadeType.ALL)
    private List<Task> tasks;

}
