package com.agendamento.hotel.service;

import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.model.Reserva;
import com.agendamento.hotel.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {
    private final QuartoRepository quartoRepository;

    @Autowired
    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    public Quarto saveQuarto(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    public Optional<Quarto> findOne(Long id) {
        return quartoRepository.findById(id);
    }

    public List<Quarto> listAllQuarto() {
        return quartoRepository.findAll();
    }

    public Quarto update(Quarto quarto) {
        return  quartoRepository.save(quarto);
    }

    public void delete(long id) {
        Quarto quarto = quartoRepository.getOne(id);
        quartoRepository.delete(quarto);
    }

    public List<Quarto> searchByDate(LocalDate data_entrada, LocalDate data_saida){
        return quartoRepository.searchByDate(data_entrada, data_saida);
    }
}
