package com.example.demo.controllers;

import com.example.demo.dtos.request.PostDto;
import com.example.demo.dtos.response.PostResponseDto;
import com.example.demo.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create_post")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.createPost(postDto));
    }

    @GetMapping("/post/users/{userId}")
    public ResponseEntity<List<PostResponseDto>> getAllPostByUser(@PathVariable("userId") Long userId){
        List<PostResponseDto> postDtoList = postService.getAllPostByUser(userId);
        return ResponseEntity.ok(postDtoList);
    }

    @GetMapping("/post/users/{postId}/{userId}")
    public ResponseEntity<PostResponseDto> getPostByUser(@PathVariable("postId") Long postId,
                                                 @PathVariable("userId") Long userId){
        return ResponseEntity.ok(postService.getPostByUser(postId,userId));
    }
    @PutMapping("/post/users/{postId}/{userId}")
    public ResponseEntity<PostResponseDto> updatePostByUser(@PathVariable("postId")Long postId,
                                                            @PathVariable("userId")Long userId,
                                                            @Valid @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePostByUser(postDto,postId,userId));
    }
    @PatchMapping("/post/users/{postId}/{userId}")
    public ResponseEntity<PostResponseDto> updatePostTitleByUser(@PathVariable("postId")Long postId,
                                                            @PathVariable("userId")Long userId,
                                                            @Valid @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePostTitleByUser(postDto,postId,userId));
    }
    @PatchMapping("/post/user/{postId}/{userId}")
    public ResponseEntity<PostResponseDto> updatePostContentByUser(@PathVariable("postId")Long postId,
                                                                 @PathVariable("userId")Long userId,
                                                                 @Valid @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePostContentByUser(postDto,postId,userId));
    }


    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> removePost(@PathVariable("postId") Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.ok("Post with ID " + postId + " deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete post with ID " + postId);
        }
    }

}
