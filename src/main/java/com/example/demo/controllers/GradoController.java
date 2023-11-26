package com.example.demo.controllers;

import com.example.demo.modelo.alumno.RepositorioAlumno;
import com.example.demo.modelo.alumnoGrado.RepositorioAlumnoGrado;
import com.example.demo.modelo.grado.DatosGrado;
import com.example.demo.modelo.grado.Grado;
import com.example.demo.modelo.grado.RepositorioGrado;
import com.example.demo.modelo.profesor.Profesor;
import com.example.demo.modelo.profesor.RepositorioProfesor;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String grados(@ModelAttribute DatosGrado datosGrado, Model model) {
        model.addAttribute("datosGrados", datosGrado);
        List<Grado> grados = repositorioGrado.findAll();
        model.addAttribute("grados", grados);
        List<Profesor> profesores = repositorioProfesor.findAll();
        model.addAttribute("profesor", profesores);
        return "grados";
    }


    @PostMapping("/grados")
    public String crearAlumno(@ModelAttribute DatosGrado datosGrado, Model model
    ) {
        Grado grado = new Grado(datosGrado);
        repositorioGrado.save(grado);
        return "redirect:grados";
    }

    @GetMapping("/eliminar-grado")
    public String eliminar(@RequestParam Long id) {

        Optional<Grado> gradoById = repositorioGrado.findById(id);

        if (gradoById.isPresent()) {
            Grado grado = gradoById.get();
            System.out.println("==============Este es el id de grado=========" + grado.getId());
            // Eliminar el profesor
        }

        return "redirect:grados";
    }


    @PostMapping("/actualizar-grado")
    @Transactional
    public String crearAlumno(@RequestParam Long idGrado, @RequestParam Long idProfe, RedirectAttributes redirectAttributes) {
        try {
            Optional<Grado> gradoById = repositorioGrado.findById(idGrado);

            if (gradoById.isPresent()) {
                Grado grado = gradoById.get();
                Optional<Profesor> profesorById = repositorioProfesor.findById(idProfe);

                if (profesorById.isPresent()) {
                    Profesor profesor = profesorById.get();
                    grado.setProfesor(profesor);
                } else {
                    throw new ValidationException("Error: No existe el profesor con el ID proporcionado");
                }
            } else {
                throw new ValidationException("Error: No existe el grado con el ID proporcionado");
            }

            return "redirect:/grados";
        } catch (ValidationException ex) {
            String message = ex.getMessage();
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/grados";
        }
    }



}
