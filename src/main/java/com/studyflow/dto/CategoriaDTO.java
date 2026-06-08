package com.studyflow.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "Nome da categoria e obrigatorio.")
    private String nome;

    private String descricao;

    @NotBlank(message = "Cor e obrigatoria.")
    private String cor;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Long id, String nome, String descricao, String cor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.cor = cor;
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
