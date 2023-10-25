package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "User_Table")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank(message = "required")
    @Size(min = 2, max = 25)
    private String names;
    @NotBlank(message = "required")
    @Size(min = 10, max = 25)
    @Email(message = "put correct email")
    private String email;
    @NotBlank(message = "required")
    @Size(min = 11,max = 11,message = "phone Number must be 11 digits")
    private String phoneNumber;
    @NotBlank(message = "required")
    @Size(min = 8, max = 15)
    private String password;
    @Transient
    @Size(min = 8, max = 15)
    private String confirmPassword;
    private String role;
    @NotBlank(message = "required")
    private String address;
    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> post;
    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> commentEntity;
    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likeEntity;
}
