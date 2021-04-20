package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/listar")
    public String listarUsuarios(Model model){
        model.addAttribute("listaUsuarios",usuarioRepository.findAll());
        return "usuarios/listaUsuarios";
    }
    @GetMapping("/usuario/newForm")
    public String nuevoUsuario(){
        return "usuarios/nuevoUsuario";
    }
    @GetMapping("/usuario/listar")
    public String editarUsuario(Usuario usario){
        return "usuarios/editarUsuarios";
    }
    @GetMapping("/usuario/listar")
    public String guardarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
        return  "redirect:/usuario/listar";
    }
}
