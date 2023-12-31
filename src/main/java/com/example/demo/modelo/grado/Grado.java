package com.example.demo.modelo.grado;

import com.example.demo.modelo.profesor.Profesor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "grado")
@Entity(name = "Grado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Grado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    public Grado(DatosGrado datosGrado) {
        this.nombre = datosGrado.nombre();
        this.profesor = datosGrado.profesor();
    }


    public void actualizarDatos(DatosGrado datosGrado) {
        if (datosGrado.profesor() != null){
            this.profesor = datosGrado.profesor();
        }
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }


}
