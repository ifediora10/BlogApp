package com.example.demo.dtos.response;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDto {
    private Long AdminId;
    private String names;
    private String email;
    private String phoneNumber;
    private String role;
    private String address;
}
