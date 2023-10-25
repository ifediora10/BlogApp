package com.example.demo.dtos.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    @Size(min = 2, max = 50)
    private String title;
    @Size(min = 2, max = 2000)
    private String content;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;
    private Long userId;
}
