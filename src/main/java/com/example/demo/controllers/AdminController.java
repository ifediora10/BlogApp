package com.example.demo.controllers;

import com.example.demo.dtos.request.AdminDto;
import com.example.demo.dtos.request.AdminLoginDto;
import com.example.demo.dtos.response.*;
import com.example.demo.services.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<AdminResponseDto> registerAdmin(@Valid @RequestBody AdminDto adminDto){
        return ResponseEntity.ok(adminService.RegisterNewAdmin(adminDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AdminResponseDto> loginAdmin(@Valid @RequestBody AdminLoginDto adminDto){
        return ResponseEntity.ok(adminService.loginAdmin(adminDto));
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsersByAdmin(){
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPostsByAdmin(){
        return ResponseEntity.ok(adminService.getAllPosts());
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByAdmin(){
        return ResponseEntity.ok(adminService.getAllComments());
    }

    @GetMapping("/likes")
    public ResponseEntity<List<LikeResponseDto>> getAllLikesByAdmin(){
        return ResponseEntity.ok(adminService.getAllLikes());
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUserByAdmin(@PathVariable("userId")Long userId){
        try {
            adminService.deleteUser(userId);
            return ResponseEntity.ok("User with id " + userId + " deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("You can't delete user with id " + userId + "because it does not exist");
        }
    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable("postId")Long postId){
        try {
            adminService.DeletePost(postId);
            return ResponseEntity.ok("Post with id " + postId + " deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("You can't delete post with id " + postId + " because it does not exist");
        }
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<String> deleteCommentByAdmin(@PathVariable("commentId")Long commentId){
        try {
            adminService.DeleteComment(commentId);
            return ResponseEntity.ok("Comment with id + " + commentId + " deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("You can't deleted comment with id " + commentId + " because it does not exist");
        }
    }

    @DeleteMapping("/delete-like/{likeId}")
    public ResponseEntity<String> deleteLikeByAdmin(@PathVariable("likeId")Long likeId){
       try {
           adminService.DeleteLike(likeId);
           return ResponseEntity.ok("Like with id " + likeId + " deleted");
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("You can't delete like with id " + likeId + " because it does not exist");
       }
    }

}
