package com.agendamento.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity

public class Hospede implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 80, message = "Nome deve ter entre 3 a 80 carateres")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 digitos")
    private String cpf;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate data_nascimento;

    @NotBlank(message = "Telefone é obrigatório")
    @Size(min = 8, max = 25, message = "Telefone deve ter entre 8 a 25 digitos")
    private String telefone;

    @NotBlank(message = "Nacionalidade é obrigatória")
    @Size(min = 3, max = 80, message = "Nacionalidade deve ter entre 3 a 80 carateres")
    private String nacionalidade;

    public Hospede() {
    }

    public Hospede(Long id, String nome, String cpf, String email, LocalDate data_nascimento, String telefone,
                   String nacionalidade) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.nacionalidade = nacionalidade;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }


}