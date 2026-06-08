package com.studyflow.dto;

import jakarta.validation.constraints.NotBlank;

public class MateriaDTO {

    private Long id;

    @NotBlank(message = "Nome da materia e obrigatorio.")
    private String nome;

    private String descricao;

    @NotBlank(message = "Periodo e obrigatorio.")
    private String periodo;

    public MateriaDTO() {
    }

    public MateriaDTO(Long id, String nome, String descricao, String periodo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.periodo = periodo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
