package com.example.demo.modelo.alumno;

import com.example.demo.modelo.alumno.Alumno;
import com.example.demo.modelo.grado.Grado;

public record DatosActualizarAlumno(
        Long id,
        String nombre,
        String apellido,
        String genero
) {
}
