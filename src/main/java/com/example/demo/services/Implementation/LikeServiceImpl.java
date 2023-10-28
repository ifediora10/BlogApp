package com.example.demo.services.Implementation;

import com.example.demo.dtos.response.LikeResponseDto;
import com.example.demo.exceptions.*;
import com.example.demo.models.entities.Like;
import com.example.demo.models.entities.Post;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.LikeRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public LikeResponseDto likePost(Long userId,Long postId ) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFound("User not found with userId "+userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new POstNotFoundException("Post not found with postId "+postId));
        Like existingLike = likeRepository.findByPostEntityPostIdAndUserEntityUserId(postId,userId);
        if (existingLike != null){
            throw new LikeAlreadyExistException("User has already liked this post.");
        }
        Like newLike = Like.builder()
                .userEntity(user)
                .postEntity(post)
                .build();
        likeRepository.save(newLike);

        return mapper.map(newLike,LikeResponseDto.class);
    }

    @Override
    public List<LikeResponseDto> getAllLikesByUser(Long userId, Long postId) {
      Post post = postRepository.findById(postId)
              .orElseThrow(()-> new POstNotFoundException("Post not found with the postId "+postId));
      if(!post.getUserEntity().getUserId().equals(userId)){
          throw new UnauthorizedUserException("You are not authorized to access Likes for this post.");
      }
      List<Like> likes = likeRepository.findAllByPostEntity(post);
      List<LikeResponseDto> likeResponseDtoList = new ArrayList<>();
      for (Like like : likes){
          LikeResponseDto likeResponseDto = LikeResponseDto.builder()
                  .likeId(like.getLikeId())
                  .postId(like.getPostEntity().getPostId())
                  .userId(like.getUserEntity().getUserId())
                  .build();
          likeResponseDtoList.add(likeResponseDto);
      }
      return likeResponseDtoList;
    }

    @Override
    public void unLikePost(Long userId, Long postId,Long likeId) {
        Like existingLike = likeRepository.findByPostEntityPostIdAndUserEntityUserId(postId,userId);
        if (existingLike == null){
            throw new LikeHaveNotExistException("User has not liked this post.");
        }
        likeRepository.deleteById(likeId);
    }
}
