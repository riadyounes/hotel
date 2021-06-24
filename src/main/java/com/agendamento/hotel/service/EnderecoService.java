package com.agendamento.hotel.service;

import com.agendamento.hotel.model.Endereco;
import com.agendamento.hotel.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco store(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> index() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> show(long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco update(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void destroy(Long id) {
        Optional<Endereco> optionalEndereco = this.show(id);

        if (optionalEndereco.isPresent()) {
            enderecoRepository.deleteById(id);
        }
    }
}
