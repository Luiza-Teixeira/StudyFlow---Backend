package com.studyflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyflow.entity.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {


    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}