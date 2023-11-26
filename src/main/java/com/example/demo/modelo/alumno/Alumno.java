package com.example.demo.modelo.alumno;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "alumno")
@Entity(name = "Alumno")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    private String nombre;
    private String apellido;
    private String genero;
    @Column(name = "fecha_de_nacimiento")
    private LocalDate fechaDeNacimiento;


    public Alumno(DatosAlumno datosAlumno) {
        this.nombre = datosAlumno.nombre();
        this.apellido = datosAlumno.apellido();
        this.genero = datosAlumno.genero();
        this.fechaDeNacimiento = datosAlumno.fechaDeNacimiento();
    }


    public void actualizarDatos(DatosActualizarAlumno datosActualizarAlumno) {
        if (!datosActualizarAlumno.nombre().isEmpty()){
            this.nombre = datosActualizarAlumno.nombre();
        }
        if (!datosActualizarAlumno.apellido().isEmpty()){
            this.apellido = datosActualizarAlumno.apellido();
        }
        if (datosActualizarAlumno.genero() != null){
            this.genero = datosActualizarAlumno.genero();
        }
    }

    @Override
    public String toString() {
        return  nombre + " " + apellido ;
    }
}
