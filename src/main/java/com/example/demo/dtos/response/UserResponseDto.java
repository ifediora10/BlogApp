package com.example.demo.dtos.response;

import jakarta.persistence.Transient;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long userId;
    private String names;
    private String email;
    private String phoneNumber;
    private String role;
    private String address;
}
