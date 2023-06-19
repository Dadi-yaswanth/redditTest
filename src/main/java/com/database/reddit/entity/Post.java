package com.database.reddit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(
            name = "is_published",
            columnDefinition = "BOOLEAN DEFAULT FALSE"
    )
    private boolean isPublished;

    @Column(name = "published_at")
    private Timestamp publishedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(
            name = "is_community_post",
            columnDefinition = "BOOLEAN DEFAULT FALSE"
    )
    private boolean is_community_post;

    @Column(name = "community_id")
    private Long communityId;

    @Column(
            name = "is_media",
            columnDefinition = "BOOLEAN DEFAULT FALSE"
    )
    private boolean isMedia;

    @OneToMany
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private List<Media> mediaList;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
