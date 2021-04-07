package com.agendamento.hotel.model;

import javax.persistence.*;


@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Float classificacao;

<<<<<<< HEAD
    @OneToOne
=======

    @OneToOne(cascade = CascadeType.ALL)
>>>>>>> bc07c857a3328621f3237dce2cba03bb07ea4c74
    private Endereco endereco;

    public Hotel() {

    }

    public Hotel(String nome, Float classificacao, Endereco endereco) {
        this.nome = nome;
        this.classificacao = classificacao;
        this.endereco = endereco;
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

    public Float getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Float classificacao) {
        this.classificacao = classificacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
