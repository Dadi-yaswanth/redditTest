package com.database.reddit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posts_media")
@Getter
@Setter
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(
            name = "is_video",
            columnDefinition = "BOOLEAN DEFAULT FALSE"
    )
    private boolean isVideo;

    @Column(name = "path")
    private String path;
}
