package com.example.demo.services.Implementation;

import com.example.demo.dtos.request.CommentDto;
import com.example.demo.dtos.response.CommentResponseDto;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public CommentResponseDto createComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public List<CommentResponseDto> getAllComments() {
        return null;
    }

    @Override
    public CommentResponseDto getCommentById(Long commentId, Long userId) {
        return null;
    }

    @Override
    public CommentResponseDto updateComment(Long commentId, Long userId) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {

    }
}
