package com.example.demo.modelo.profesor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositorioProfesor extends JpaRepository<Profesor,Long> {

}
