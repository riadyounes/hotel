package com.agendamento.hotel.service;

import com.agendamento.hotel.model.Quarto;
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

    public Quarto store(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    public List<Quarto> index() {
        return quartoRepository.findAll();
    }

    public List<Quarto> index(LocalDate data_entrada, LocalDate data_saida) {
        return quartoRepository.search(data_entrada, data_saida);
    }

    public Optional<Quarto> show(Long id) {
        return quartoRepository.findById(id);
    }

    public Quarto update(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    public void destroy(long id) {
        Optional<Quarto> optionalQuarto = this.show(id);

        if (optionalQuarto.isPresent()) {
            quartoRepository.deleteById(id);
        }
    }
}
