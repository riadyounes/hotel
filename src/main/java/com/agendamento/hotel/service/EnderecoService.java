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

    public Endereco savedEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Optional<Endereco> findOne(long id){
        return  enderecoRepository.findById(id);
    }

    public List<Endereco> ListAllEndereco() {
        return enderecoRepository.findAll();
    }

    public Endereco update(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void detele(Long id){
        Endereco endereco = enderecoRepository.getOne(id);
        enderecoRepository.delete(endereco);
    }
}
