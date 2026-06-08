package com.studyflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class EstudanteMateriaDTO {

    private Long id;

    @NotNull(message = "Data de inscricao e obrigatoria.")
    private LocalDate dataInscricao;

    @NotBlank(message = "Status e obrigatorio.")
    private String status;

    @NotNull(message = "Estudante e obrigatorio.")
    private Long estudanteId;

    @NotNull(message = "Materia e obrigatoria.")
    private Long materiaId;

    public EstudanteMateriaDTO() {
    }

    public EstudanteMateriaDTO(Long id, LocalDate dataInscricao, String status,
                               Long estudanteId, Long materiaId) {
        this.id = id;
        this.dataInscricao = dataInscricao;
        this.status = status;
        this.estudanteId = estudanteId;
        this.materiaId = materiaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getEstudanteId() {
        return estudanteId;
    }

    public void setEstudanteId(Long estudanteId) {
        this.estudanteId = estudanteId;
    }

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }
}
