package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Actividad;
import com.ipt.dashboard.entity.Area;
import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.ActividadRepository;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        System.out.println("LLEGO AQUI");
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        model.addAttribute("idpro",idproyecto);
        return "actividad/newActividad";
    }

    @PostMapping("/actividad/save")
    public String actividadSave(Actividad actividad, RedirectAttributes attr){
        if(actividad.getIdactividad() == 0 ){
            attr.addFlashAttribute("msg","Actividad creada exitosamente");
        }else{
            attr.addFlashAttribute("msg","Actividad actualizada exitosamente");
        }

        actividadRepository.save(actividad);
        return "redirect:/actividad";
    }

    @GetMapping("/actividad/edit")
    public String editActividad(@RequestParam("id") int id, Model model){

        Optional<Actividad>  actividadOpt = actividadRepository.findById(id);

        if(actividadOpt.isPresent()){
            Actividad actividad = actividadOpt.get();
            model.addAttribute("actividad",actividad);

            return "actividad/editForm";
        }else{
            return "redirect:/actividad";
        }
    }

    @GetMapping("/actividad/borrar")
    public String borrarActividad(@RequestParam("id") int id, RedirectAttributes attr1) {

        Optional<Actividad> actividadOpt = actividadRepository.findById(id);

        if (actividadOpt.isPresent()) {
            actividadRepository.deleteById(id);
            attr1.addFlashAttribute("msg","Actividad borrada exitosamente");
        }
        return "redirect:/actividad";
    }
}
