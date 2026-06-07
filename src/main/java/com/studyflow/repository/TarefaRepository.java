package com.studyflow.repository;

import com.studyflow.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByStatus(String status);

    List<Tarefa> findByMateriaId(Long materiaId);

    List<Tarefa> findByCategoriaId(Long categoriaId);
}
