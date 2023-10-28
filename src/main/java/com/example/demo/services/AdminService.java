package com.example.demo.services;

import com.example.demo.dtos.request.AdminDto;
import com.example.demo.dtos.request.AdminLoginDto;
import com.example.demo.dtos.response.*;

import java.util.List;

public interface AdminService {
    AdminResponseDto RegisterNewAdmin(AdminDto adminDto);
    AdminResponseDto loginAdmin(AdminLoginDto adminDto);
    List<UserResponseDto> getAllUsers();
    List<PostResponseDto> getAllPosts();
    List<CommentResponseDto> getAllComments();
    List<LikeResponseDto> getAllLikes();
    void deleteUser(Long userId);
    void DeletePost(Long postId);
    void DeleteComment(Long commentId);
    void DeleteLike(Long likeId);
}
