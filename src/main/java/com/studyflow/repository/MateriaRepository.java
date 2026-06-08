package com.studyflow.repository;

import com.studyflow.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    boolean existsByNomeAndPeriodo(String nome, String periodo);

    boolean existsByNomeAndPeriodoAndIdNot(String nome, String periodo, Long id);
}
