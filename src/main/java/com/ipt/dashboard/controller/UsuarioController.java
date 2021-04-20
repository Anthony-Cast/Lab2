package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Proyecto;
import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "usuarios/listaUsuarios";
    }

    @GetMapping("/usuario/newForm")
    public String nuevoUsuario() {
        return "usuarios/nuevoUsuario";
    }

    @GetMapping("/usuario/editar")
    public String editarUsuario(@RequestParam("id") String id,Model model) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            model.addAttribute("usuario",optional.get());
            return "usuarios/editarUsuario";
        }
        return "redirect:/usuario/listar";
    }

    @PostMapping("/usuario/guardar")
    public String guardarUsuario(Usuario usuario, RedirectAttributes attr) {
        Optional<Usuario> optional=usuarioRepository.findById(usuario.getCorreo());
        if(optional.isPresent()){
            attr.addFlashAttribute("msg", "Usuario editado exitosamente");
        }
        else {
            attr.addFlashAttribute("msg2", "Usuario creado exitosamente");
        }
        usuarioRepository.save(usuario);
        return "redirect:/usuario/listar";
    }
    @GetMapping("/usuario/borrar")
    public String borrarUsuario(Model model, @RequestParam("id") String id, RedirectAttributes attr){
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            usuarioRepository.deleteById(id);
            attr.addFlashAttribute("msg3", "Usuario borrado exitosamente");
        }
        return "redirect:/usuario/listar";
    }
}
