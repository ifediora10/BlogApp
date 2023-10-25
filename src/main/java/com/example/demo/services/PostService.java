package com.example.demo.services;

import com.example.demo.dtos.request.PostDto;
import com.example.demo.dtos.response.PostResponseDto;

import java.util.List;

public interface PostService {
    PostResponseDto createPost (PostDto postDto);
    List<PostResponseDto> getAllPostByUser(Long userId);
    PostResponseDto getPostByUser(Long postId, Long userId);
    PostResponseDto updatePostByUser(PostDto postDto, Long postId, Long userId);
    PostResponseDto updatePostTitleByUser(PostDto postDto, Long postId, Long userId);
    PostResponseDto updatePostContentByUser(PostDto postDto, Long postId, Long userId);
    void deletePost(Long postId);
}
