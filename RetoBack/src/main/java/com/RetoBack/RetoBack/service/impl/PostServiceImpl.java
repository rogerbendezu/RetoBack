package com.RetoBack.RetoBack.service.impl;

import com.RetoBack.RetoBack.Util.UtilGlobal;
import com.RetoBack.RetoBack.model.Post;
import com.RetoBack.RetoBack.model.Usuario;
import com.RetoBack.RetoBack.repository.PostRepository;
import com.RetoBack.RetoBack.repository.UsuarioRepository;
import com.RetoBack.RetoBack.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    UtilGlobal utilGlobal = new UtilGlobal();

    @Override
    public ArrayList<Post> listarPost(){
        return (ArrayList<Post>) postRepository.findAll();
    }

    @Override
    public Post guardarPost(Post p, String token) {
        String username = utilGlobal.ObtieneUsuarioByToke(token);
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        p.setFechaRegistro(new Date());
        p.setUsuario(usuarioOptional.get());
        return postRepository.save(p);
    }

    @Override
    public Post actualizarPost(long id,Post post, String token) {
        Optional<Post> postOptional = postRepository.findById(id);
        if(!postOptional.isPresent()){
            return null;
        }
        Post postx = postOptional.get();
        String username = utilGlobal.ObtieneUsuarioByToke(token);
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(postx.getUsuario().getId());
        if (!usuarioOptional.get().getUsername().equals(username))
        {
            return null;
        }
        post.setFechaModificacion(new Date());
        post.setFechaRegistro(postOptional.get().getFechaRegistro());
        post.setUsuario(postOptional.get().getUsuario());
        post.setId(postOptional.get().getId());
        return postRepository.save(post);

    }

    @Override
    public boolean eliminarPost(long id, String token) {
        Optional<Post> postOptional = postRepository.findById(id);
        if(!postOptional.isPresent()){
            return false;
        }
        Post postx = postOptional.get();
        String username = utilGlobal.ObtieneUsuarioByToke(token);
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(postx.getUsuario().getId());
        if (!usuarioOptional.get().getUsername().equals(username))
        {
            return false;
        }
        postRepository.delete(postOptional.get());
        return true;
    }

    @Override
    public Optional<Post> obtenerPost(long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if(!postOptional.isPresent()){
        }
        return Optional.of(postOptional.get());
    }
}
