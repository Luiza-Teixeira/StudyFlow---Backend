package com.studyflow.repository;

import com.studyflow.entity.EstudanteMateria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudanteMateriaRepository extends JpaRepository<EstudanteMateria, Long> {

    boolean existsByEstudanteIdAndMateriaId(Long estudanteId, Long materiaId);

    boolean existsByEstudanteIdAndMateriaIdAndIdNot(Long estudanteId, Long materiaId, Long id);
}
