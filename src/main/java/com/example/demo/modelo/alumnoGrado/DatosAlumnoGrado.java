package com.example.demo.modelo.alumnoGrado;

import com.example.demo.modelo.alumno.Alumno;
import com.example.demo.modelo.grado.Grado;

public record DatosAlumnoGrado(
        Alumno alumno,
        Grado grado,
        String seccion
) {
}
