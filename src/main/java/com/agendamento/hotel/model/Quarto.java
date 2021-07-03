package com.agendamento.hotel.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @NotBlank(message = "Preço é obrigatório")
    @Min(value = 0, message = "Preço deve ser positivo")
    private Float preco;

    @NotBlank(message = "Ocupação é obrigatório")
    @Min(value = 0, message = "Ocupação deve ser positivo")
    private Integer quant_ocupacao;

    @NotBlank(message = "Detalhes é obrigatório")
    private String detalhes;

    @NotBlank(message = "Hotel é obrigatório")
    @ManyToOne
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
