package com.RetoBack.RetoBack;

import com.RetoBack.RetoBack.model.Usuario;
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
public class UsuarioRepositoryTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Rollback(value = false)
    public void saveUsuarioTest(){
        Usuario usuario = new Usuario();
        usuario.setFechaRegistro(new Date());
        usuario.setName("Kiara");
        usuario.setLastName("Sanchez");
        usuario.setCellphone("987987987");
        usuario.setPassword("147147147");
        usuario.setUsername("kiara@abc.com");

        usuarioRepository.save(usuario);

        Assertions.assertThat(usuario.getId() > 0);
    }

    @Test
    public void getUsuarioTest(){
        Usuario usuario = usuarioRepository.findById(1L).get();

        Assertions.assertThat(usuario.getId()).isEqualTo(1L);
    }

    @Test
    public void getListUsuarioTest(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        Assertions.assertThat(usuarios.size()).isGreaterThan(0);
    }

    @Test
    public void updateUsuarioTest(){
        Usuario usuario = usuarioRepository.findById(1L).get();
        usuario.setCellphone("999999999");
        Usuario usuarioUpdated = usuarioRepository.save(usuario);

        Assertions.assertThat(usuarioUpdated.getCellphone()).isEqualTo("999999999");
    }

    @Test
    public void deleteUsuarioTest(){

        Usuario usuario = usuarioRepository.findById(1L).get();
        usuarioRepository.delete(usuario);
        Usuario usuario1 = null;
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername("kiara@abc.com");
        if (optionalUsuario.isPresent()){
            usuario1 = optionalUsuario.get();
        }
        Assertions.assertThat(usuario1).isNull();
    }
}
