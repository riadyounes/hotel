package com.agendamento.hotel.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 80, message = "Nome deve ter entre 3 a 80 carateres")
    private String nome;

    @NotBlank(message = "Classificação é obrigatória")
    @Min(value = 0, message = "Classificação deve ser maior ou igual a 0")
    @Max(value = 5, message = "Classificação deve ser menor ou igual a 5")
    private Float classificacao;

    @NotBlank(message = "Endereço é obrigatório")
    @OneToOne
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
