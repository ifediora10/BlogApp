package com.example.demo.services;

import com.example.demo.dtos.request.CommentDto;
import com.example.demo.dtos.response.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(CommentDto commentDto);
    List<CommentResponseDto> getAllComments();
    CommentResponseDto getCommentById(Long commentId, Long userId);
    CommentResponseDto updateComment(Long commentId, Long userId);
    void  deleteComment(Long commentId, Long userId);
}
