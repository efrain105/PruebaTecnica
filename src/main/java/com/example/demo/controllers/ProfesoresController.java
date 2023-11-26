package com.example.demo.controllers;

import com.example.demo.modelo.alumno.RepositorioAlumno;
import com.example.demo.modelo.alumnoGrado.RepositorioAlumnoGrado;
import com.example.demo.modelo.grado.DatosGrado;
import com.example.demo.modelo.grado.Grado;
import com.example.demo.modelo.grado.RepositorioGrado;
import com.example.demo.modelo.profesor.DatosActualizarProfesor;
import com.example.demo.modelo.profesor.DatosProfesor;
import com.example.demo.modelo.profesor.Profesor;
import com.example.demo.modelo.profesor.RepositorioProfesor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String alumnos(@ModelAttribute DatosProfesor datosProfesor, @ModelAttribute DatosGrado datosGrado, Model model
    , DatosActualizarProfesor datosActualizarProfesor){
        model.addAttribute("datosActualizar",datosActualizarProfesor);
        model.addAttribute("datosProfesor", datosProfesor);
        List<Profesor> profesor = repositorioProfesor.findAll();
        model.addAttribute("profesor", profesor);
        List<Grado> grados = repositorioGrado.findAll();
        model.addAttribute("grados", grados);
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
        Optional<Profesor> profesor = repositorioProfesor.findById(id);
        if (profesor.isPresent()) {
            Profesor profesor1 = profesor.get();
            List<Grado> grados = repositorioGrado.findByProfesor_Id(profesor1.getId());
            // Establecer profesor a null en cada grado
            for (Grado grado : grados) {
                grado.setProfesor(null);
            }
            // Eliminar el profesor
            repositorioProfesor.delete(profesor1);
        }
        return "redirect:profesores";
    }


    @PostMapping("/profesoresac")
    @Transactional
    public String actualizarMedico(@ModelAttribute DatosActualizarProfesor datosActualizarProfesor, Model model) {
        Profesor profesor = repositorioProfesor.getReferenceById(datosActualizarProfesor.id());
        profesor.actualizarDatos(datosActualizarProfesor);
        return "redirect:profesores";
    }


}
