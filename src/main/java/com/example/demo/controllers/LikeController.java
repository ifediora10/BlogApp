package com.example.demo.controllers;

import com.example.demo.dtos.response.LikeResponseDto;
import com.example.demo.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/like/{userId}/{postId}")
    public ResponseEntity<LikeResponseDto> likePost(@PathVariable("userId")Long userId,
                                                    @PathVariable("postId")Long postId){
        return ResponseEntity.ok(likeService.likePost(userId, postId));
    }

    @GetMapping("/likes/{userId}/{postId}")
    public ResponseEntity<List<LikeResponseDto>> getAllLikeByUser(@PathVariable("userId")Long userId,
                                                                  @PathVariable("postId")Long postId){
        return ResponseEntity.ok(likeService.getAllLikesByUser(userId, postId));
    }

    @DeleteMapping("/unlike/{userId}/{postId}/{likeId}")
    public ResponseEntity<String> unLikePost(@PathVariable("userId")Long userId,
                                             @PathVariable("postId")Long postId,
                                             @PathVariable("likeId")Long likeId){
        try {
            likeService.unLikePost(userId, postId, likeId);
            return ResponseEntity.ok("Like with likeId "+likeId+ " deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Like with likeId "+likeId + " can't be deleted because it does not exist");
        }
    }
}
