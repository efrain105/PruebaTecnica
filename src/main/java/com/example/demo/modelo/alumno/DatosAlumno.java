package com.example.demo.modelo.alumno;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;



public record DatosAlumno(
        @Pattern(regexp = "^[A-Za-z]+$", message = "")
        String nombre,
        @Pattern(regexp = "^[A-Za-z]+$", message = "")
        String apellido,
        String genero,
        LocalDate fechaDeNacimiento
) {
}
