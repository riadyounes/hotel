package com.agendamento.hotel.repository;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository <Reserva, Long>{


    List<Reserva> searchByHospede(Optional<Hospede> hospede);

    List<Reserva> searchByQuarto(Optional<Quarto> quarto);
}
