package com.ipt.dashboard.controller;

import com.fasterxml.jackson.datatype.jdk8.Jdk8UnwrappingOptionalBeanPropertyWriter;
import com.ipt.dashboard.entity.Proyecto;
import com.ipt.dashboard.repository.ActividadRepository;
import com.ipt.dashboard.repository.ProyectoRepository;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ActividadRepository actividadRepository;

    @GetMapping(value = {"/listar", " "})
    public String listarProyectos(Model model){
        model.addAttribute("listaProyectos", proyectoRepository.findAll());
        return "/proyecto/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoProyecto(Model model){

        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "/proyecto/nuevo";

    }

    @PostMapping("/guardar")
    public String guardarProyecto(Proyecto proyecto, RedirectAttributes redirectAttributes){
        Optional<Proyecto> proyectoOpt=proyectoRepository.findById(proyecto.getIdproyecto());
        if(proyectoOpt.isPresent()){
            redirectAttributes.addFlashAttribute("msg", "Proyecto editado exitosamente");
        }
        else {
            redirectAttributes.addFlashAttribute("msg2", "Proyecto creado exitosamente");
        }
        proyectoRepository.save(proyecto);
        return "redirect:/proyecto/listar";
    }

    @GetMapping("/editar")
    public String editarProyecto(Model model, @RequestParam("id") int id){
        Optional<Proyecto> optionalProyecto = proyectoRepository.findById(id);
        if(optionalProyecto.isPresent()){
            Proyecto proyecto = optionalProyecto.get();
            model.addAttribute("proyecto", proyecto);
            model.addAttribute("listaUsuarios", usuarioRepository.findAll());
            model.addAttribute("listaActividades", actividadRepository.listarActividadesPorProyecto(id));
            return "/proyecto/editar";
        } else{
            return "redirect:/proyecto/listar";
        }
    }

    @GetMapping("/borrar")
    public String borrarProyecto(Model model, @RequestParam("id") int id, RedirectAttributes redirectAttributes){
        Optional<Proyecto> optionalProyecto = proyectoRepository.findById(id);
        if(optionalProyecto.isPresent()){
            proyectoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("msg3", "Proyecto borrado exitosamente");
        }
        return "redirect:/proyecto/listar";
    }
}
