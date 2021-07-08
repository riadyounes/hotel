package com.agendamento.hotel.repository;

import com.agendamento.hotel.enums.ReservaEstado;
import com.agendamento.hotel.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("select r from Reserva r " +
            "where r.estado= :estado " +
            "or r.quarto.id= :quarto_id " +
            "or r.hospede.id= :hospede_id " +
            "or r.data_entrada >= :data_entrada " +
            "or r.data_saida <= :data_saida")
    List<Reserva> search(
            @Param("data_entrada") LocalDate data_entrada,
            @Param("data_saida") LocalDate data_saida,
            @Param("estado") ReservaEstado estado,
            @Param("hospede_id") Long hospede_id,
            @Param("quarto_id") Long quarto_id);
}
