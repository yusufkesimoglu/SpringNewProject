package com.project.repos;

import com.project.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);
    List<Comment> findByUserId(Long userId);
    List<Comment> findByPostIdAndUserId(Long postId, Long userId);


}
