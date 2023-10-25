package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Post_Table")
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(nullable = false)
    private String title;
    private String content;

    @CreationTimestamp
    @Column (updatable = false)
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "postEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> commentEntity;

    @OneToMany(mappedBy = "postEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likeEntity;

    @ManyToOne (fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
                                               CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id",nullable = false)
    private User userEntity;

    @Transient
    private Long commentCount;

    @Transient
    private Long likeCount;
}
