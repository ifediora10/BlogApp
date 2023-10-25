package com.example.demo.dtos.response;

import com.example.demo.models.entities.Post;
import com.example.demo.models.entities.User;
import jakarta.persistence.JoinColumn;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String comment;
    private LocalDateTime commentDate;
    private Long postId;
    private Long userId;
}
