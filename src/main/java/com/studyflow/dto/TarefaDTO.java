package com.studyflow.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TarefaDTO {

    private Long id;

    @NotBlank(message = "Titulo da tarefa e obrigatorio.")
    private String titulo;

    private String descricao;

    @NotNull(message = "Prazo e obrigatorio.")
    @FutureOrPresent(message = "Prazo nao pode ser anterior a data atual.")
    private LocalDate prazo;

    private String status;

    @NotNull(message = "Materia e obrigatoria.")
    private Long materiaId;

    @NotNull(message = "Categoria e obrigatoria.")
    private Long categoriaId;

    public TarefaDTO() {
    }

    public TarefaDTO(Long id, String titulo, String descricao, LocalDate prazo,
                     String status, Long materiaId, Long categoriaId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.status = status;
        this.materiaId = materiaId;
        this.categoriaId = categoriaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
