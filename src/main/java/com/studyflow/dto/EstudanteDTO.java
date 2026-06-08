package com.studyflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// DTO separa os dados da API da entidade JPA usada internamente pela aplicacao.
public class EstudanteDTO {

    private Long id;

    @NotBlank(message = "Nome e obrigatorio.")
    private String nome;

    @NotBlank(message = "Email e obrigatorio.")
    @Email(message = "Email deve ter formato valido.")
    private String email;

    @NotBlank(message = "Senha e obrigatoria.")
    private String senha;

    public EstudanteDTO() {
    }

    public EstudanteDTO(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
