package br.com.fiap.sga.controller.dto;

import br.com.fiap.sga.entity.Aluno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AlunoCreateDTO {


    private Long id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    private EnderecoDTO endereco;

    public AlunoCreateDTO() {}

    public AlunoCreateDTO(String nome, String email, EnderecoDTO endereco) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }

    public AlunoCreateDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.endereco = new EnderecoDTO(aluno.getEndereco());

    }

    public String getNome() { return this.nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public EnderecoDTO getEndereco() { return this.endereco; }
    public void setEndereco(EnderecoDTO endereco) { this.endereco = endereco; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
