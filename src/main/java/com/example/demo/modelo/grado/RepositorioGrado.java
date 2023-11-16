package com.example.demo.modelo.grado;

import com.example.demo.modelo.profesor.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RepositorioGrado extends JpaRepository<Grado,Long> {


    List<Grado> findByProfesor_Id(Long id);

    @Transactional
    @Modifying
    @Query("update Grado g set g.profesor = ?1 where g.profesor = ?2")
    int updateProfesorByProfesor(Profesor profesor, Profesor profesor1);


}
