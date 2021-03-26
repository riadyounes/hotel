package com.agendamento.hotel.repository;

import com.agendamento.hotel.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository <Reserva, Long>{
}
