package com.agendamento.hotel.repository;

import com.agendamento.hotel.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartoRepository extends JpaRepository <Quarto, Long> {

}
