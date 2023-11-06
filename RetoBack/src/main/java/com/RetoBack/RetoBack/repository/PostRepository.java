package com.RetoBack.RetoBack.repository;

import com.RetoBack.RetoBack.model.Post;
import com.RetoBack.RetoBack.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByText(String text);
}
