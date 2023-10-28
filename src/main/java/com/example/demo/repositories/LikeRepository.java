package com.example.demo.repositories;

import com.example.demo.models.entities.Like;
import com.example.demo.models.entities.Post;
import com.example.demo.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    Like findByPostEntityPostIdAndUserEntityUserId(Long postId, Long userId);

    List<Like> findAllByPostEntity(Post post);
}
