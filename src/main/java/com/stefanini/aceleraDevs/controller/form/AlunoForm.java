package com.stefanini.aceleraDevs.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.stefanini.aceleraDevs.model.Aluno;
import com.stefanini.aceleraDevs.model.DadosPessoais;
import com.stefanini.aceleraDevs.model.Turma;

public class AlunoForm {

    @NotEmpty
    @Length(min = 5)
    private String nome;

    @NotEmpty
    @Length(min = 5)
    private String matricula;

    private DadosPessoais dadosPessoais;

    private Turma turma;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Aluno converter() {
        return new Aluno(null, nome, matricula, dadosPessoais, turma);
    }

}
