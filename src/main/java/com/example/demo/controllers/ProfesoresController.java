package com.example.demo.controllers;

import com.example.demo.modelo.alumno.Alumno;
import com.example.demo.modelo.alumno.DatosAlumno;
import com.example.demo.modelo.alumno.RepositorioAlumno;
import com.example.demo.modelo.alumnoGrado.AlumnoGrado;
import com.example.demo.modelo.alumnoGrado.DatosAlumnoGrado;
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
public class ProfesoresController {


    @Autowired
    RepositorioAlumno repositorioAlumno;
    @Autowired
    RepositorioAlumnoGrado repositorioAlumnoGrado;
    @Autowired
    RepositorioGrado repositorioGrado;
    @Autowired
    RepositorioProfesor repositorioProfesor;

    @GetMapping("/profesores")
    public String alumnos(@ModelAttribute DatosProfesor datosProfesor, @ModelAttribute DatosGrado datosGrado, Model model){
        model.addAttribute("datosProfesor", datosProfesor);
        List<Profesor> profesor = repositorioProfesor.findAll();
        model.addAttribute("profesor", profesor);
        return "profesores";
    }

    @PostMapping("/profesores")
    public String crearAlumno(@ModelAttribute DatosProfesor datosProfesor, Model model

    ){
        Profesor profesor = new Profesor(datosProfesor);
        repositorioProfesor.save(profesor);
        return "redirect:profesores";
    }

    @GetMapping("/eliminar-profesor")
    public String eliminar(@RequestParam Long id){
        Optional<Profesor> alumno = repositorioProfesor.findById(id);
        alumno.ifPresent(repositorioProfesor::delete);
        return "redirect:profesores";
    }


}
