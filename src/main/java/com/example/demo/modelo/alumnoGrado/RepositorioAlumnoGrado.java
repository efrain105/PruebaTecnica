package com.example.demo.modelo.alumnoGrado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAlumnoGrado extends JpaRepository<AlumnoGrado,Long> {
    AlumnoGrado findByAlumno_Id(Long id);
}
