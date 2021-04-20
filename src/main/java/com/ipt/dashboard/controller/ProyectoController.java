package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Proyecto;
import com.ipt.dashboard.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    ProyectoRepository proyectoRepository;
    @GetMapping(value = {"/listar", " "})
    public String listarProyectos(Model model){
        model.addAttribute("listaProyectos", proyectoRepository.findAll());
        return "/proyecto/lista.html";
    }
    @GetMapping("/nuevo")
    public String nuevoProyecto(){
        return "/proyecto/nuevo";
    }
    @PostMapping("/guardar")
    public String guardarProyecto(Proyecto proyecto){

    }
}
