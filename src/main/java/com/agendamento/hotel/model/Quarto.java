package com.agendamento.hotel.model;

import javax.persistence.*;

@Entity
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private Float preco;
    private Integer quant_ocupacao;
    private String detalhes;

    @ManyToOne(cascade = CascadeType.ALL)
    private Hotel hotel;

    public Quarto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Integer getQuant_ocupacao() {
        return quant_ocupacao;
    }

    public void setQuant_ocupacao(Integer quant_ocupacao) {
        this.quant_ocupacao = quant_ocupacao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
