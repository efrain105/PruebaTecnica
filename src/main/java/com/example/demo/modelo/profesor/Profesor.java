package com.example.demo.modelo.profesor;

import com.example.demo.modelo.alumno.DatosAlumno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "profesor")
@Entity(name = "Profesor")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nombre;
    private String apellido;
    private String genero;



    public Profesor(DatosProfesor datosProfesor) {
        this.nombre = datosProfesor.nombre();
        this.apellido = datosProfesor.apellido();
        this.genero = datosProfesor.genero();
    }


    @Override
    public String toString() {
        return nombre + " " + apellido;
    }


    public void actualizarDatos(DatosActualizarProfesor datosActualizarProfesor) {
        if (datosActualizarProfesor.nombre() != null){
            this.nombre = datosActualizarProfesor.nombre();
        }
        if (datosActualizarProfesor.apellido() != null){
            this.apellido = datosActualizarProfesor.apellido();
        }
    }

}
