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
public class AlumnoController {
    @Autowired
    RepositorioAlumno repositorioAlumno;
    @Autowired
    RepositorioAlumnoGrado repositorioAlumnoGrado;
    @Autowired
    RepositorioGrado repositorioGrado;
    @Autowired
    RepositorioProfesor repositorioProfesor;

    @GetMapping("/alumnos")
    public String alumnos(@ModelAttribute DatosAlumno datosAlumno, @ModelAttribute DatosGrado datosGrado, Model model){
        model.addAttribute("datosAlumno", datosAlumno);
        model.addAttribute("datosGrado", datosGrado);
        List<AlumnoGrado> alumnos = repositorioAlumnoGrado.findAll();
        model.addAttribute("alumnos", alumnos);
        List<Grado> grados = repositorioGrado.findAll();
        model.addAttribute("grados", grados);
        return "alumnos";
    }

    @PostMapping("/alumnos")
    public String crearAlumno(@ModelAttribute DatosAlumno datosAlumno, Model model,
    @RequestParam Long idGrado
    ){
        Alumno alumno = new Alumno(datosAlumno);
        Optional<Grado> grado = repositorioGrado.findById(idGrado);
        Grado grado1 = grado.get();
        AlumnoGrado alumnoGrado = new AlumnoGrado(new DatosAlumnoGrado(alumno, grado1, "A"));
        repositorioAlumno.save(alumno);
        repositorioAlumnoGrado.save(alumnoGrado);
        return "redirect:alumnos";
    }

    @GetMapping("/eliminar-alumno")
    public String eliminar(@RequestParam Long id){
        AlumnoGrado byAlumnoId = repositorioAlumnoGrado.findByAlumno_Id(id);
        repositorioAlumnoGrado.delete(byAlumnoId);
        Optional<Alumno> alumno = repositorioAlumno.findById(id);
        alumno.ifPresent(repositorioAlumno::delete);
        return "redirect:alumnos";
    }

}
