package com.example.demo.services;

import com.example.demo.dtos.request.LoginDto;
import com.example.demo.dtos.request.SignupDto;
import com.example.demo.dtos.request.UserDto;
import com.example.demo.dtos.response.UserResponseDto;

public interface UserService {
  UserResponseDto registerNewUser(SignupDto signupDto);
  UserResponseDto loginUser(LoginDto loginDto);
  UserResponseDto updateUser(UserDto userDto,Long userId, String email);
  UserResponseDto updateUserName(UserDto userDto,Long userId, String email);
  UserResponseDto updateUserPhoneNumber(UserDto userDto,Long userId, String email);
  UserResponseDto updateUserPassword(UserDto userDto,Long userId, String email);
  UserResponseDto updateUserAddress(UserDto userDto,Long userId, String email);
  void deleteUser(Long userId);
}
