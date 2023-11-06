package com.RetoBack.RetoBack.controller;

import com.RetoBack.RetoBack.Jwt.JwtService;
import com.RetoBack.RetoBack.model.Post;
import com.RetoBack.RetoBack.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    JwtService jwtService;

    @GetMapping("/listar")
    public ArrayList<Post> listarPost(){
        return postService.listarPost();
    }

    @PostMapping("/guardar")
    public Post guardarPost(@RequestBody Post post, @RequestHeader("Authorization") String token){
        return postService.guardarPost(post,token);
    }

    @PutMapping("/actualizar/{id}")
    public Post actualizarPost(@PathVariable("id") long id, @RequestBody Post post, @RequestHeader("Authorization") String token){
        return postService.actualizarPost(id,post,token);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarPost(@PathVariable("id") long id,@RequestHeader("Authorization") String token){
        if (postService.eliminarPost(id,token)){
            return "Se ha eliminado el post";
        }
        else
        {
            return "No se ha eliminado el post";
        }
    }
/*
    @GetMapping("/obtener/{id}")
    public Optional<Post> obtenerPost(@PathVariable("id") long id){
        return postService.obtenerPost(id);
    }
    */
}
