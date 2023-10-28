package com.example.demo.controllers;

import com.example.demo.dtos.request.PostDto;
import com.example.demo.dtos.response.PostPageResponseDto;
import com.example.demo.dtos.response.PostResponseDto;
import com.example.demo.services.PostService;
import com.example.demo.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/user/{userId}")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create-post")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostDto postDto,
                                                      @PathVariable Long userId){
        return ResponseEntity.ok(postService.createPost(postDto, userId));
    }

    @GetMapping("/post")
    public ResponseEntity<PostPageResponseDto> getAllPostByUserId(@PathVariable("userId") Long userId,
                                                                  @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
                                                                  @RequestParam(value = "pageSize", defaultValue =AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
                                                                  @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                                  @RequestParam(value = "sortDir", defaultValue =AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        PostPageResponseDto postDtoList = postService.getAllPostByUser(userId,pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(postDtoList);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostResponseDto> getPostByUserIdAndPostId(@PathVariable("postId") Long postId,
                                                 @PathVariable("userId") Long userId){
        return ResponseEntity.ok(postService.getPostByUser(postId,userId));
    }

    @PutMapping("/post/{postId}/update-post")
    public ResponseEntity<PostResponseDto> updatePostByUser(@PathVariable("postId")Long postId,
                                                            @PathVariable("userId")Long userId,
                                                            @Valid @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePostByUser(postDto,postId,userId));
    }

    @PatchMapping("/post/{postId}/update-post-title")
    public ResponseEntity<PostResponseDto> updatePostTitleByUser(@PathVariable("postId")Long postId,
                                                            @PathVariable("userId")Long userId,
                                                            @Valid @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePostTitleByUser(postDto,postId,userId));
    }

    @PatchMapping("/post/{postId}/update-post-content")
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
