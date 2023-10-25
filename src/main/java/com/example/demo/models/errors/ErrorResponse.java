package com.example.demo.models.errors;

import jakarta.servlet.http.HttpSession;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime localDateTime;
    private String debugMessage;

}
