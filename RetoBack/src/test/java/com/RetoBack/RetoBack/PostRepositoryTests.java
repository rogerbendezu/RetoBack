package com.RetoBack.RetoBack;

import com.RetoBack.RetoBack.model.Post;
import com.RetoBack.RetoBack.model.Usuario;
import com.RetoBack.RetoBack.repository.PostRepository;
import com.RetoBack.RetoBack.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Rollback(value = false)
    public void savePostTest(){
        Usuario usuario = new Usuario();
        usuario.setFechaRegistro(new Date());
        usuario.setName("Kiara");
        usuario.setLastName("Sanchez");
        usuario.setCellphone("987987987");
        usuario.setPassword("147147147");
        usuario.setUsername("kiara@abc.com");

        usuarioRepository.save(usuario);


        Post post = new Post();
        post.setFechaRegistro(new Date());
        post.setText("Hola hola");
        post.setUsuario(new Usuario());
        post.getUsuario().setId(1L);
        postRepository.save(post);

        Assertions.assertThat(post.getId() > 0);
    }

    @Test
    public void getPostTest(){
        Post post = postRepository.findById(1L).get();

        Assertions.assertThat(post.getId()).isEqualTo(1L);
    }

    @Test
    public void getListPostTest(){
        List<Post> post = postRepository.findAll();

        Assertions.assertThat(post.size()).isGreaterThan(0);
    }

    @Test
    public void updatePostTest(){
        Post post = postRepository.findById(1L).get();
        post.setText("Good Good");
        Post postUpdated = postRepository.save(post);

        Assertions.assertThat(postUpdated.getText()).isEqualTo("Good Good");
    }

    @Test
    public void deletePostTest(){

        Post post = postRepository.findById(1L).get();
        postRepository.delete(post);
        Post post1 = null;
        Optional<Post> optionalPost = postRepository.findByText("Good Good");
        if (optionalPost.isPresent()){
            post1 = optionalPost.get();
        }
        Assertions.assertThat(post1).isNull();
    }
}
