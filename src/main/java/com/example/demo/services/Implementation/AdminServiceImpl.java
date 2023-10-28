package com.example.demo.services.Implementation;

import com.example.demo.dtos.request.AdminDto;
import com.example.demo.dtos.request.AdminLoginDto;
import com.example.demo.dtos.response.*;
import com.example.demo.exceptions.*;
import com.example.demo.models.entities.*;
import com.example.demo.repositories.*;
import com.example.demo.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public AdminResponseDto RegisterNewAdmin(AdminDto adminDto) {
        if (adminRepository.existsByEmail(adminDto.getEmail())){
            throw new DuplicateEmailException("Email already exist");
        }
        if (!adminDto.getPassword().equals(adminDto.getConfirmPassword())){
            throw new PasswordMissMatchException("password miss Match try again");
        }
        Admin newAdmin = mapper.map(adminDto,Admin.class);
        newAdmin.setRole("ADMIN");

        Admin savedAdmin = adminRepository.save(newAdmin);
        return mapper.map(savedAdmin,AdminResponseDto.class);
    }

    @Override
    public AdminResponseDto loginAdmin(AdminLoginDto adminDto) {
      Admin admin = adminRepository.findByEmailAndPassword(adminDto.getEmail(), adminDto.getPassword())
              .orElseThrow(()->new WrongDetailsException("Email or password incorrect"));
        return mapper.map(admin,AdminResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> mapper.map(user,UserResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(post -> mapper.map(post, PostResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponseDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream()
                .map(comment -> mapper.map(comment,CommentResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LikeResponseDto> getAllLikes() {
        List<Like> likes = likeRepository.findAll();

        return likes.stream()
                .map(like -> mapper.map(like,LikeResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)){
            throw new UserNotFound("User with id " + userId + " does not exist.");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public void DeletePost(Long postId) {
       if(!postRepository.existsById(postId)){
           throw new POstNotFoundException("Post with id " + postId + " does not exist. ");
       }
       postRepository.deleteById(postId);
    }

    @Override
    public void DeleteComment(Long commentId) {
        if(!commentRepository.existsById(commentId)){
            throw new CommentNotFoundException("Comment with id " + commentId + " does not exist. ");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public void DeleteLike(Long likeId) {
       if(!likeRepository.existsById(likeId)){
           throw new LikeHaveNotExistException("Like with id " + likeId + " does not exist. ");
       }
       likeRepository.deleteById(likeId);
    }
}
