package com.stefanini.aceleraDevs.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.stefanini.aceleraDevs.model.Turma;

public class TurmaDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String nome;

    public TurmaDTO() {
    }

    public TurmaDTO(Turma turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();
    }

    public TurmaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static List<TurmaDTO> converter(List<Turma> turmas) {
        return turmas.stream().map(TurmaDTO::new).collect(Collectors.toList());
    }
}
