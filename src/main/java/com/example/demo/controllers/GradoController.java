package com.example.demo.controllers;

import com.example.demo.modelo.alumno.Alumno;
import com.example.demo.modelo.alumno.RepositorioAlumno;
import com.example.demo.modelo.alumnoGrado.AlumnoGrado;
import com.example.demo.modelo.alumnoGrado.RepositorioAlumnoGrado;
import com.example.demo.modelo.grado.DatosGrado;
import com.example.demo.modelo.grado.Grado;
import com.example.demo.modelo.grado.RepositorioGrado;
import com.example.demo.modelo.profesor.DatosProfesor;
import com.example.demo.modelo.profesor.Profesor;
import com.example.demo.modelo.profesor.RepositorioProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class GradoController {


    @Autowired
    RepositorioAlumno repositorioAlumno;
    @Autowired
    RepositorioAlumnoGrado repositorioAlumnoGrado;
    @Autowired
    RepositorioGrado repositorioGrado;
    @Autowired
    RepositorioProfesor repositorioProfesor;

    @GetMapping("/grados")
    public String grados(@ModelAttribute DatosGrado datosGrado, Model model){
        model.addAttribute("datosGrados", datosGrado);
        List<Grado> grados = repositorioGrado.findAll();
        model.addAttribute("grados", grados);
        List<Profesor> profesores = repositorioProfesor.findAll();
        model.addAttribute("profesor",profesores);
        return "grados";
    }


    @PostMapping("/grados")
    public String crearAlumno(@ModelAttribute DatosGrado datosGrado, Model model
    ){
        Grado grado = new Grado(datosGrado);
        repositorioGrado.save(grado);
        return "redirect:grados";
    }

    @GetMapping("/eliminar-grado")
    public String eliminar(@RequestParam Long id){
        Optional<Grado> byAlumnoId = repositorioGrado.findById(id);
        byAlumnoId.ifPresent(repositorioGrado::delete);
        return "redirect:grados";
    }


}
