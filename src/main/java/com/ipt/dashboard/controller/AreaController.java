package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Area;
import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.AreaRepository;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AreaController {

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/area")
    public String areaList(Model model){

        model.addAttribute("areaList", areaRepository.findAll());

        return "area/list";
    }

    @GetMapping("/area/new")
    public String areaNew(){

        return "area/newArea";
    }

    @PostMapping("/area/save")
    public String areaSave(Area area, RedirectAttributes attr){
        if(area.getIdarea() == 0 ){
            attr.addFlashAttribute("msg2","Area creada exitosamente");
        }else{
            attr.addFlashAttribute("msg","Area actualizada exitosamente");
        }

        areaRepository.save(area);
        return "redirect:/area";
    }


    @GetMapping("/area/edit")
    public String editArea(@RequestParam("id") int id, Model model){

        Optional<Area> areaOpt = areaRepository.findById(id);

        if(areaOpt.isPresent()){
            Area area = areaOpt.get();
            model.addAttribute("area",area);

            List<Usuario> usuarioOpt = usuarioRepository.listarUsuariosPorArea(id);
            model.addAttribute("listaUsuarios", usuarioOpt);

            return "area/editForm";
        }else{
            return "redirect:/area";
        }
    }

    @GetMapping("/area/borrar")
    public String borrarArea(@RequestParam("id") int id, RedirectAttributes attr1) {

        Optional<Area> areaOpt = areaRepository.findById(id);

        if (areaOpt.isPresent()) {
            areaRepository.deleteById(id);
            attr1.addFlashAttribute("msg3","Area borrada exitosamente");
        }
        return "redirect:/area";
    }

}
