package com.agendamento.hotel.service;

import com.agendamento.hotel.model.*;
import com.agendamento.hotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva store(Reserva reserva) {
        reserva.setEstado(EnumEstado.RESERVADO);
        return reservaRepository.save(reserva);
    }

    public Reserva finalizar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("reserva não encontrada!!!"));

        if (!reserva.getEstado().equals(EnumEstado.EM_ANDAMENTO)) {
            throw new RuntimeException("Essa reserva não pode ser finalizada, pois não esta em andamento");
        }

        if (reserva.getEstado().equals(EnumEstado.FINALIZADO)) {
            throw new RuntimeException("Reserva já finalizada!!!");
        }

        reserva.setEstado(EnumEstado.FINALIZADO);

        return reservaRepository.save(reserva);
    }

    public Reserva emAndamento(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("reserva não encontrada!!!"));

        if (!reserva.getEstado().equals(EnumEstado.RESERVADO)) {
            throw new RuntimeException("Essa reserva ja foi finalizada ou cancelada");
        }
        if (reserva.getEstado().equals(EnumEstado.EM_ANDAMENTO)) {
            throw new RuntimeException("Reserva já em andamento!!!");
        }

        reserva.setEstado(EnumEstado.EM_ANDAMENTO);

        return reservaRepository.save(reserva);
    }

    public Reserva cancelar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("reserva não encontrada!!!"));

        if (!reserva.getEstado().equals(EnumEstado.RESERVADO)) {
            throw new RuntimeException("Essa reserva ja foi finalizada ou cancelada");
        }
        if (reserva.getEstado().equals(EnumEstado.CANCELADO)) {
            throw new RuntimeException("Reserva já foi cancelada!!!");
        }

        reserva.setEstado(EnumEstado.CANCELADO);

        return reservaRepository.save(reserva);
    }

    public List<Reserva> index() {
        return reservaRepository.findAll();
    }

    public List<Reserva> searchByDate(LocalDate data_entrada, LocalDate data_saida) {
        return reservaRepository.searchByDate(data_entrada, data_saida);
    }

    public Optional<Reserva> show(long id) {
        return reservaRepository.findById(id);
    }

    public Reserva update(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void destroy(Long id) {
        Optional<Reserva> optionalReserva = this.show(id);

        if (optionalReserva.isPresent()) {
            reservaRepository.deleteById(id);
        }
    }

    public List<Reserva> getByHospede(Optional<Hospede> hospede) {
        if (hospede.isPresent()) {
            return reservaRepository.searchByHospede(hospede.get());
        }
        return null;
    }

    public List<Reserva> getByQuarto(Optional<Quarto> quarto) {
        if (quarto.isPresent()) {
            return reservaRepository.searchByQuarto(quarto.get());
        }
        return null;
    }
}
