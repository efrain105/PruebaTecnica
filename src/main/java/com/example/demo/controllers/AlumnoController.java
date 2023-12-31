package com.example.demo.controllers;
import com.example.demo.modelo.alumno.Alumno;
import com.example.demo.modelo.alumno.DatosActualizarAlumno;
import com.example.demo.modelo.alumno.DatosAlumno;
import com.example.demo.modelo.alumno.RepositorioAlumno;
import com.example.demo.modelo.alumnoGrado.AlumnoGrado;
import com.example.demo.modelo.alumnoGrado.DatosAlumnoGrado;
import com.example.demo.modelo.alumnoGrado.RepositorioAlumnoGrado;
import com.example.demo.modelo.grado.DatosGrado;
import com.example.demo.modelo.grado.Grado;
import com.example.demo.modelo.grado.RepositorioGrado;
import com.example.demo.modelo.profesor.RepositorioProfesor;
import jakarta.transaction.Transactional;
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
    public String alumnos(@ModelAttribute DatosAlumno datosAlumno, @ModelAttribute DatosGrado datosGrado, Model model,
                          @ModelAttribute DatosActualizarAlumno datosActualizarAlumno){
        model.addAttribute("datosAlumnoac",datosActualizarAlumno);
        model.addAttribute("datosAlumno", datosAlumno);
        model.addAttribute("datosGrado", datosGrado);
        List<AlumnoGrado> alumnos = repositorioAlumnoGrado.findAll();
        model.addAttribute("alumnos", alumnos);
        List<Grado> grados = repositorioGrado.findAll();
        model.addAttribute("grados", grados);
        return "alumnos";
    }

    @PostMapping("/alumnos")
    public String crearAlumno(@ModelAttribute DatosAlumno datosAlumno,
    @RequestParam Long idGrado, @RequestParam String nombreSeccion
    ){
        Alumno alumno = new Alumno(datosAlumno);
        Optional<Grado> grado = repositorioGrado.findById(idGrado);
        Grado grado1 = grado.get();
        AlumnoGrado alumnoGrado = new AlumnoGrado(new DatosAlumnoGrado(alumno, grado1, nombreSeccion));
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



    @PostMapping("/alumnosac")
    @Transactional
    public String actualizarMedico(@ModelAttribute DatosActualizarAlumno datosActualizarAlumno, Model model, @RequestParam Long alumnoId) {
        Alumno alumno = repositorioAlumno.getReferenceById(alumnoId);
        alumno.actualizarDatos(datosActualizarAlumno);
        return "redirect:alumnos";
    }


    @GetMapping("/editar")
    public String editaralumno(@ModelAttribute DatosAlumno datosAlumno, @ModelAttribute DatosGrado datosGrado, Model model,
                               @ModelAttribute DatosActualizarAlumno datosActualizarAlumno, @RequestParam Long id){
        model.addAttribute("datosAlumnoac",datosActualizarAlumno);
        model.addAttribute("datosAlumno", datosAlumno);
        model.addAttribute("datosGrado", datosGrado);
        AlumnoGrado alumnos = repositorioAlumnoGrado.findByAlumno_Id(id);
        model.addAttribute("alumnos", alumnos);
        List<Grado> grados = repositorioGrado.findAll();
        model.addAttribute("grados", grados);
        return "editar";
    }


}
