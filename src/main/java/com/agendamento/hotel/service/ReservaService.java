package com.agendamento.hotel.service;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.model.Hotel;
import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.model.Reserva;
import com.agendamento.hotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva store(Reserva reserva){
        return reservaRepository.save(reserva);
    }

    public List<Reserva> index(){
        return reservaRepository.findAll();
    }

    public Optional<Reserva> show(long id){
        return  reservaRepository.findById(id);
    }

    public Reserva update(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void destroy(Long id) {
        Optional<Reserva> optionalReserva = this.show(id);

        if (optionalReserva.isPresent()){
            reservaRepository.deleteById(id);
        }
    }

    public List<Reserva> getByHospede(Optional<Hospede> hospede){
        return reservaRepository.searchByHospede(hospede);
    }

    public List<Reserva> getByQuarto(Optional<Quarto> quarto){
        return reservaRepository.searchByQuarto(quarto);
    }

}
