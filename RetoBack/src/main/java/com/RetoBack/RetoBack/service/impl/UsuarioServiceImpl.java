package com.RetoBack.RetoBack.service.impl;

import com.RetoBack.RetoBack.model.Usuario;
import com.RetoBack.RetoBack.repository.UsuarioRepository;
import com.RetoBack.RetoBack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public ArrayList<Usuario> listarUsuario(){
        ArrayList<Usuario> listaUsuario = (ArrayList<Usuario>) usuarioRepository.findAll();
        return listaUsuario;
    }

    @Override
    public Usuario guardarUsuario(Usuario u) {
        u.setFechaRegistro(new Date());
        return usuarioRepository.save(u);
    }

    @Override
    public Usuario actualizarUsuario(long id,Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(!usuarioOptional.isPresent()){
        }
        usuario.setFechaModificacion(new Date());
        usuario.setFechaRegistro(usuarioOptional.get().getFechaRegistro());
        usuario.setId(usuarioOptional.get().getId());
        return usuarioRepository.save(usuario);

    }

    @Override
    public boolean eliminarUsuario(long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(!usuarioOptional.isPresent()){
            return false;
        }
        usuarioRepository.delete(usuarioOptional.get());
        return true;
    }

    @Override
    public Optional<Usuario> obtenerUsuario(long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(!usuarioOptional.isPresent()){
        }
        return Optional.of(usuarioOptional.get());
    }

}
