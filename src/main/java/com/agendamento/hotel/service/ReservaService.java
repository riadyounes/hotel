package com.agendamento.hotel.service;

import com.agendamento.hotel.enums.ReservaEstado;
import com.agendamento.hotel.model.Reserva;
import com.agendamento.hotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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
        reserva.setEstado(ReservaEstado.RESERVADO);
        return reservaRepository.save(reserva);
    }

    public Reserva finalizar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("reserva não encontrada!!!"));

        if (!reserva.getEstado().equals(ReservaEstado.EM_ANDAMENTO)) {
            throw new RuntimeException("Essa reserva não pode ser finalizada, pois não esta em andamento");
        }

        if (reserva.getEstado().equals(ReservaEstado.FINALIZADO)) {
            throw new RuntimeException("Reserva já finalizada!!!");
        }

        reserva.setEstado(ReservaEstado.FINALIZADO);

        return reservaRepository.save(reserva);
    }

    public Reserva emAndamento(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("reserva não encontrada!!!"));

        if (!reserva.getEstado().equals(ReservaEstado.RESERVADO)) {
            throw new RuntimeException("Essa reserva ja foi finalizada ou cancelada");
        }

        if (reserva.getEstado().equals(ReservaEstado.EM_ANDAMENTO)) {
            throw new RuntimeException("Reserva já em andamento!!!");
        }

        reserva.setEstado(ReservaEstado.EM_ANDAMENTO);

        return reservaRepository.save(reserva);
    }

    public Reserva cancelar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("reserva não encontrada!!!"));

        if (!reserva.getEstado().equals(ReservaEstado.RESERVADO)) {
            throw new RuntimeException("Essa reserva ja foi finalizada ou cancelada");
        }

        if (reserva.getEstado().equals(ReservaEstado.CANCELADO)) {
            throw new RuntimeException("Reserva já foi cancelada!!!");
        }

        reserva.setEstado(ReservaEstado.CANCELADO);

        return reservaRepository.save(reserva);
    }

    public List<Reserva> index() {
        return reservaRepository.findAll();
    }

    public List<Reserva> index(@Nullable LocalDate data_entrada, @Nullable LocalDate data_saida, @Nullable ReservaEstado estado, @Nullable Long hospede_id, @Nullable Long quarto_id) {
        return reservaRepository.search(data_entrada, data_saida, estado, hospede_id, quarto_id);
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
}
