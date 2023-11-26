package com.example.demo.modelo.alumnoGrado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioAlumnoGrado extends JpaRepository<AlumnoGrado,Long> {
    AlumnoGrado findByAlumno_Id(Long id);

    List<AlumnoGrado> findByGrado_Id(Long id);
}
