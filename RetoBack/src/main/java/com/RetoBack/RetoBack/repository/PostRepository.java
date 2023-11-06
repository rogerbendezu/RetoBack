package com.RetoBack.RetoBack.repository;

import com.RetoBack.RetoBack.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
