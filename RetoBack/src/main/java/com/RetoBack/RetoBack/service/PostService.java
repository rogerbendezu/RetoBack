package com.RetoBack.RetoBack.service;

import com.RetoBack.RetoBack.model.Post;

import java.util.ArrayList;
import java.util.Optional;

public interface PostService {

    ArrayList<Post> listarPost();
    Post guardarPost(Post p, String token);
    Post actualizarPost(long id,Post post, String token);
    boolean eliminarPost(long id,String token);
    Optional<Post> obtenerPost(long id);
}
