package com.example.demo.modelo.alumno;

import java.time.LocalDate;

public record DatosAlumno(
        String nombre,
        String apellido,
        String genero,
        LocalDate fechaDeNacimiento
) {
}
