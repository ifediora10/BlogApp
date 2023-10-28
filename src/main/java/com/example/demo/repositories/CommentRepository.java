package com.example.demo.repositories;

import com.example.demo.models.entities.Comment;
import com.example.demo.models.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostEntity(Post post);
}
