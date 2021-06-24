package com.agendamento.hotel.service;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HospedeService {

    private final HospedeRepository hospedeRepository;

    @Autowired
    public HospedeService(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public Hospede store(Hospede hospede) {
        return hospedeRepository.save(hospede);
    }

    public List<Hospede> index() {
        return hospedeRepository.findAll();
    }

    public Optional<Hospede> show(long id) {
        return hospedeRepository.findById(id);
    }

    public Hospede update(Hospede hospede) {
        return hospedeRepository.save(hospede);
    }

    public void destroy(long id) {
        Optional<Hospede> optionalHospede = this.show(id);

        if (optionalHospede.isPresent()) {
            hospedeRepository.deleteById(id);
        }
    }


}
