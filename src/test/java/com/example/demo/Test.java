package com.example.demo;

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
import com.example.demo.util.JPAutil;
import jakarta.persistence.EntityManager;

import javax.management.remote.JMXPrincipal;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {



        LocalDate localDate = LocalDate.now();

        DatosAlumno datosAlumno = new DatosAlumno("Juan", "Carmine", "M", localDate);
        Alumno alumno = new Alumno();


        DatosProfesor datosProfesor = new DatosProfesor("Eduardo", "Condor", "M");
        Profesor profesor = new Profesor(datosProfesor);


        DatosGrado datosGrado = new DatosGrado("Primero", profesor);
        Grado grado = new Grado(datosGrado);


        DatosAlumnoGrado datosAlumnoGrado = new DatosAlumnoGrado(alumno,grado,"A");
        AlumnoGrado alumnoGrado = new AlumnoGrado(datosAlumnoGrado);


    }
}
