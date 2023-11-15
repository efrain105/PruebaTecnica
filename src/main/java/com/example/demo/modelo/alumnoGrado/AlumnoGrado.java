package com.example.demo.modelo.alumnoGrado;


import com.example.demo.modelo.alumno.Alumno;
import com.example.demo.modelo.grado.Grado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Table(name = "alumnogrado")
@Entity(name = "AlumnoGrado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class AlumnoGrado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "grado_id")
    private Grado grado;

    private String seccion;

    public AlumnoGrado(DatosAlumnoGrado datosAlumnoGrado) {
        this.grado = datosAlumnoGrado.grado();
        this.alumno = datosAlumnoGrado.alumno();
        this.seccion = datosAlumnoGrado.seccion();
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }


}
