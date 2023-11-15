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
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class Test {

    @Autowired
    RepositorioAlumno repositorioAlumno;
    @Autowired
    RepositorioProfesor repositorioProfesor;
    @Autowired
    RepositorioGrado repositorioGrado;
    @Autowired
    RepositorioAlumnoGrado repositorioAlumnoGrado;



    LocalDate localDate = LocalDate.now();

    DatosAlumno datosAlumno = new DatosAlumno("Juan", "Carmine", "M", localDate);
    Alumno alumno = new Alumno(datosAlumno);


    DatosProfesor datosProfesor = new DatosProfesor("Eduardo", "Condor", "M");
    Profesor profesor = new Profesor(datosProfesor);


    DatosGrado datosGrado = new DatosGrado("Primero", profesor);
    Grado grado = new Grado(datosGrado);


    DatosAlumnoGrado datosAlumnoGrado = new DatosAlumnoGrado(alumno, grado,"A");
    AlumnoGrado alumnoGrado = new AlumnoGrado(datosAlumnoGrado);


    @GetMapping("/index")
    public String test(){
    return "index";
    }

}
