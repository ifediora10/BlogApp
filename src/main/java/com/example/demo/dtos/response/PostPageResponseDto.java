package com.example.demo.dtos.response;

import com.example.demo.models.entities.Post;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PostPageResponseDto {
    private List<PostResponseDto> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private Boolean lastPage;
}
