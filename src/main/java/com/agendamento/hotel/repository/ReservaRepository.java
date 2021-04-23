package com.agendamento.hotel.repository;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository <Reserva, Long>{


    List<Reserva> searchByHospede(Hospede hospede);

    List<Reserva> searchByQuarto(Quarto quarto);

    @Query("select r from Reserva r where r.data_entrada >= :data_entrada and r.data_saida <= :data_saida")
    List<Reserva> searchByDate(
        @Param("data_entrada") LocalDate data_entrada,
        @Param("data_saida") LocalDate data_saida);
}
