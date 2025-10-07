package br.com.fiap.sga.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "DISCIPLINA") // nao é obrigatório
public class Disciplina {

    @Id //indica primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //diz como vai ser
    // gerado o valor da primary key
    private Long id;

    @Column(name = "nome") // nao é obrigatorio
    @NotBlank
    private String nome;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
