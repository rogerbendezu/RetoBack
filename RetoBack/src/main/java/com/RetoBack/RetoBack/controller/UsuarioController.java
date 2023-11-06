package com.RetoBack.RetoBack.controller;

import com.RetoBack.RetoBack.model.Usuario;
import com.RetoBack.RetoBack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/listar")
    public ArrayList<Usuario> listarUsuario(){
        ArrayList<Usuario> listaUsuario = usuarioService.listarUsuario();
        return listaUsuario;
    }

    @PostMapping("/guardar")
    public Usuario guardarUsuario(@RequestBody Usuario usuario){
        return usuarioService.guardarUsuario(usuario);
    }

    @PutMapping("/actualizar/{id}")
    public Usuario actualizarUsuario(@PathVariable("id") long id,@RequestBody Usuario usuario){
        return usuarioService.actualizarUsuario(id,usuario);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") long id){
        if (usuarioService.eliminarUsuario(id)){
            return "Se ha eliminado el usuario";
        }
        else
        {
            return "No se ha eliminado el usuario";
        }
    }

    @GetMapping("/obtener/{id}")
    public Optional<Usuario> obtenerUsuario(@PathVariable("id") long id){
        return usuarioService.obtenerUsuario(id);
    }
}
