package com.RetoBack.RetoBack.service;

import com.RetoBack.RetoBack.model.Usuario;

import java.util.ArrayList;
import java.util.Optional;

public interface UsuarioService{

    ArrayList<Usuario> listarUsuario();
    Usuario guardarUsuario(Usuario u);
    Usuario actualizarUsuario(long id,Usuario usuario);
    boolean eliminarUsuario(long id);
    Optional<Usuario> obtenerUsuario(long id);
}
