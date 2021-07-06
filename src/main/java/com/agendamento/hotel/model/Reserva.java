package com.agendamento.hotel.model;

import com.agendamento.hotel.enums.ReservaEstado;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data de entrada é obrigatória")
    private LocalDate data_entrada;

    @NotNull(message = "Data de saida é obrigatória")
    private LocalDate data_saida;

    private ReservaEstado estado;

    @NotNull(message = "Preço total é obrigatório")
    @Min(value = 0, message = "Preço total deve ser positivo")
    private Float preco_total;

    @NotNull(message = "Usuário é obrigatório")
    @OneToOne
    private Usuario usuario;

    @NotNull(message = "Hospede é obrigatório")
    @ManyToOne
    private Hospede hospede;

    @NotNull(message = "Quarto é obrigatório")
    @ManyToOne
    private Quarto quarto;

    public Reserva() {
    }

    public Reserva(LocalDate data_entrada, LocalDate data_saida, ReservaEstado estado, Float preco_total, Usuario usuario, Hospede hospede, Quarto quarto) {
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.estado = estado;
        this.preco_total = preco_total;
        this.usuario = usuario;
        this.hospede = hospede;
        this.quarto = quarto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDate data_entrada) {
        this.data_entrada = data_entrada;
    }

    public LocalDate getData_saida() {
        return data_saida;
    }

    public void setData_saida(LocalDate data_saida) {
        this.data_saida = data_saida;
    }

    public Float getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(Float preco_total) {
        this.preco_total = preco_total;
    }

    public ReservaEstado getEstado() {
        return estado;
    }

    public void setEstado(ReservaEstado estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
}
