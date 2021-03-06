package com.agendamento.hotel.repository;

import com.agendamento.hotel.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospedeRepository extends JpaRepository <Hospede, Long> {

}
