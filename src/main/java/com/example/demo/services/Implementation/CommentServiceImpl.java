package com.example.demo.services.Implementation;

import com.example.demo.dtos.request.CommentDto;
import com.example.demo.dtos.response.CommentResponseDto;
import com.example.demo.exceptions.CommentNotFoundException;
import com.example.demo.exceptions.POstNotFoundException;
import com.example.demo.exceptions.UnauthorizedUserException;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.models.entities.Comment;
import com.example.demo.models.entities.Post;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentResponseDto createComment(Long userId, Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new POstNotFoundException("Post not Found with id, " + postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found with userId " + userId));
        Comment newComment = Comment.builder()
                .postEntity(post)
                .userEntity(user)
                .comment(commentDto.getComment())
                .build();

        Comment savedComment = commentRepository.save(newComment);

        return CommentResponseDto.builder()
                .commentId(savedComment.getCommentId())
                .comment(savedComment.getComment())
                .commentDate(savedComment.getCommentDate())
                .postId(savedComment.getPostEntity().getPostId())
                .userId(savedComment.getUserEntity().getUserId())
                .build();
    }
    @Override
    public List<CommentResponseDto> getAllComments(Long userId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new POstNotFoundException("Post not Found with id, " + postId));
        if (!post.getUserEntity().getUserId().equals(userId)){
            throw new UnauthorizedUserException("You are not authorized to access comments for this post.");
        }
        List<Comment> comments = commentRepository.findAllByPostEntity(post);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : comments){
            CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                    .commentId(comment.getCommentId())
                    .comment(comment.getComment())
                    .commentDate(comment.getCommentDate())
                    .postId(comment.getPostEntity().getPostId())
                    .userId(comment.getUserEntity().getUserId())
                    .build();
            commentResponseDtoList.add(commentResponseDto);
        }
        return commentResponseDtoList;
    }

    @Override
    public CommentResponseDto getCommentById(Long userId,Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new POstNotFoundException("Post not Found with id, " + postId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new CommentNotFoundException("Comment not found with commentId " + commentId));

        if(!post.getUserEntity().getUserId().equals(userId) && !comment.getUserEntity().getUserId().equals(userId)){
            throw  new UnauthorizedUserException("You are not authorized to access comments for this post.");
        }
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .comment(comment.getComment())
                .commentDate(comment.getCommentDate())
                .postId(comment.getPostEntity().getPostId())
                .userId(comment.getUserEntity().getUserId())
                .build();
    }

    @Override
    public CommentResponseDto updateComment(CommentDto commentDto, Long commentId, Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new POstNotFoundException("Post not Found with id, " + postId));
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()){
            Comment existingComment = optionalComment.get();
            if(post.getPostId().equals(postId)&& existingComment.getCommentId().equals(commentId)){
                Comment updatedComment = commentRepository.saveAndFlush(existingComment);
                return mapper.map(updatedComment,CommentResponseDto.class);
            }else {
                throw new UnauthorizedUserException("You are not authorized to access this comment ");
            }
        }else {
            throw new CommentNotFoundException("comment not found with commentId "+commentId);
        }
    }

    @Override
    public void deleteComment(Long commentId,Long postId, Long userId) {
         Optional<Comment> optionalComment = commentRepository.findById(commentId);
         if(optionalComment.isPresent()){
             Comment existingComment = optionalComment.get();
             if(!existingComment.getPostEntity().getPostId().equals(postId)&& !existingComment.getUserEntity().getUserId().equals(userId)){
                 throw  new UnauthorizedUserException("You are not authorized to delete this comment ");
             }else {
                 commentRepository.deleteById(commentId);
             }
         }else {
             throw new CommentNotFoundException("comment not found with commentId "+commentId);
         }

    }
}
