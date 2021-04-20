package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Actividad;
import com.ipt.dashboard.entity.Area;
import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.ActividadRepository;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ActividadController {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/actividad")
    public String actividadList(@RequestParam("idproyecto") int idproyecto,Model model){

        model.addAttribute("actividadList", actividadRepository.listarActividadesPorProyecto(idproyecto));

        return "actividad/list";
    }

    @GetMapping("/actividad/new")
    public String actividadNew(@RequestParam("idproyecto") int idproyecto, Model model){
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        model.addAttribute("idpro",idproyecto);
        return "actividad/newActividad";
    }

    @PostMapping("/actividad/save")
    public String actividadSave(Actividad actividad, RedirectAttributes attr){
        Optional<Actividad> optional = actividadRepository.findById(actividad.getIdactividad());
        if(optional.isPresent()){
            attr.addFlashAttribute("msg","Actividad actualizada exitosamente");
        }else{
            attr.addFlashAttribute("msg2","Actividad creada exitosamente");
        }

        actividadRepository.save(actividad);
        return "redirect:/proyecto/editar?id="+actividad.getIdproyecto();
    }

    @GetMapping("/actividad/edit")
    public String editActividad(@RequestParam("idactividad") int id, Model model){
        Optional<Actividad>  actividadOpt = actividadRepository.findById(id);
        Actividad actividad = actividadOpt.get();
        if(actividadOpt.isPresent()){
            model.addAttribute("actividad",actividad);
            return "actividad/editForm";
        }else{
            return "redirect:/proyecto/editar?id="+actividad.getIdproyecto();
        }
    }

    @GetMapping("/actividad/borrar")
    public String borrarActividad(@RequestParam("idactividad") int id, RedirectAttributes attr1) {
        Optional<Actividad> actividadOpt = actividadRepository.findById(id);
        Actividad actividad=actividadOpt.get();
        if (actividadOpt.isPresent()) {
            actividadRepository.deleteById(id);
            attr1.addFlashAttribute("msg3","Actividad borrada exitosamente");

        }
        return "redirect:/proyecto/editar?id="+actividad.getIdproyecto();
    }
}
