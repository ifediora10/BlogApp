package com.example.demo.services.Implementation;

import com.example.demo.dtos.request.LoginDto;
import com.example.demo.dtos.request.SignupDto;
import com.example.demo.dtos.request.UserDto;
import com.example.demo.dtos.response.UserResponseDto;
import com.example.demo.exceptions.*;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public UserResponseDto registerNewUser(SignupDto signupDto) {
        if (userRepository.existsByEmail(signupDto.getEmail())){
            throw new DuplicateEmailException("Email already exist!");
        }
        if (signupDto.getPassword().equals(signupDto.getConfirmPassword())){
            throw new PasswordMissMatchException("password miss Match try again");
        }
        User newUser = mapper.map(signupDto,User.class);
        newUser.setRole("USER");
        User savedUser = userRepository.save(newUser);

        return mapper.map(savedUser,UserResponseDto.class);
    }

    @Override
    public UserResponseDto loginUser(LoginDto loginDto) {
        User user = userRepository
                .findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword())
                .orElseThrow(()-> new WrongDetailsException("Email or password incorrect"));
        return mapper.map(user,UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(UserDto userDto, Long userId, String email) {
        Optional<User> optionalUser = userRepository.findByUserIdAndEmail(userId,email);
        if (optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            if(existingUser.getUserId().equals(userId)&&existingUser.getEmail().equals(email)){
                existingUser.setNames(userDto.getNames());
                existingUser.setPhoneNumber(userDto.getPhoneNumber());
                existingUser.setPassword(userDto.getPassword());
                existingUser.setAddress(userDto.getAddress());
                User updatedUser = userRepository.saveAndFlush(existingUser);
                return mapper.map(updatedUser,UserResponseDto.class);
            }else {
                throw new UnauthorizedUserException("You are not authorized to access this "+ userId);
            }
        }else {
            throw new UserNotFound("User with this userId "+userId);
        }
    }

    @Override
    public UserResponseDto updateUserName(UserDto userDto, Long userId, String email) {
        Optional<User> optionalUser = userRepository.findByUserIdAndEmail(userId,email);
            if(optionalUser.isPresent()){
                User existingUser = optionalUser.get();
                if (existingUser.getUserId().equals(userId)&& existingUser.getEmail().equals(email)){
                    existingUser.setNames(userDto.getNames());
                    User updatedUser = userRepository.saveAndFlush(existingUser);
                    return mapper.map(updatedUser,UserResponseDto.class);
                }else {
                    throw new UnauthorizedUserException("You are not authorized to access this "+ userId);
                }
            }else {
                throw new UserNotFound("User with this userId "+userId);
            }
    }

    @Override
    public UserResponseDto updateUserPhoneNumber(UserDto userDto, Long userId, String email) {
        Optional<User> optionalUser = userRepository.findByUserIdAndEmail(userId,email);
        if(optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            if (existingUser.getUserId().equals(userId)&& existingUser.getEmail().equals(email)){
                existingUser.setPhoneNumber(userDto.getPhoneNumber());
                User updatedUser = userRepository.saveAndFlush(existingUser);
                return mapper.map(updatedUser,UserResponseDto.class);
            }else {
                throw new UnauthorizedUserException("You are not authorized to access this "+ userId);
            }
        }else {
            throw new UserNotFound("User with this userId "+userId);
        }
    }

    @Override
    public UserResponseDto updateUserPassword(UserDto userDto, Long userId, String email) {
        Optional<User> optionalUser = userRepository.findByUserIdAndEmail(userId,email);
        if(optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            if (existingUser.getUserId().equals(userId)&& existingUser.getEmail().equals(email)){
                existingUser.setPassword(userDto.getPassword());
                User updatedUser = userRepository.saveAndFlush(existingUser);
                return mapper.map(updatedUser,UserResponseDto.class);
            }else {
                throw new UnauthorizedUserException("You are not authorized to access this "+ userId);
            }
        }else {
            throw new UserNotFound("User with this userId "+userId);
        }
    }

    @Override
    public UserResponseDto updateUserAddress(UserDto userDto, Long userId, String email) {
        Optional<User> optionalUser = userRepository.findByUserIdAndEmail(userId,email);
        if(optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            if (existingUser.getUserId().equals(userId)&& existingUser.getEmail().equals(email)){
                existingUser.setAddress(userDto.getAddress());
                User updatedUser = userRepository.saveAndFlush(existingUser);
                return mapper.map(updatedUser,UserResponseDto.class);
            }else {
                throw new UnauthorizedUserException("You are not authorized to access this "+ userId);
            }
        }else {
            throw new UserNotFound("User with this userId "+userId);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            userRepository.deleteById(userId);
        }else
      throw new UserNotFound("User not found with userId "+userId);
    }
}
