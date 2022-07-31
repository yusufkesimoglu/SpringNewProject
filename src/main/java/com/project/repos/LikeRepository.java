package com.project.repos;

import com.project.entites.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByUserIdAndPostId(Long postId, Long userId);

    List<Like> findByPostId(Long postId);

    List<Like> findByUserId(Long userId);
}
