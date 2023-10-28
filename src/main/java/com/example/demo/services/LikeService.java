package com.example.demo.services;

import com.example.demo.dtos.response.LikeResponseDto;

import java.util.List;

public interface LikeService {
    LikeResponseDto likePost(Long userId,Long postId);
    List<LikeResponseDto> getAllLikesByUser(Long userId,Long postId);
    void unLikePost(Long likeId, Long postId, Long userId);
}
