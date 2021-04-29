package com.agendamento.hotel.repository;

import com.agendamento.hotel.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository <Quarto, Long> {

    @Query("select q from Quarto q inner join Reserva r on q.id=r.quarto.id where r.data_entrada >= :data_entrada " +
            "and r.data_saida <= :data_saida and r.estado like 'finalizado' or r.estado like 'cancelado' ")
    List<Quarto> searchByDate(
            @Param("data_entrada") LocalDate data_entrada,
            @Param("data_saida") LocalDate data_saida);

}
