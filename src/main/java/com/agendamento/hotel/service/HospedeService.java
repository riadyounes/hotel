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

    public Hospede cadastraHospede(Hospede hospede) {
        return hospedeRepository.save(hospede);
    }

    public Optional<Hospede> findOne(long id){
        return  hospedeRepository.findById(id);
    }

    public List<Hospede> ListAllHospede() {
        return hospedeRepository.findAll();
    }

    public Hospede update(Hospede hospede) {
        return hospedeRepository.save(hospede);

    }

    public void delete(long id) {
        Hospede hospede = hospedeRepository.getOne(id);
        hospedeRepository.delete(hospede);
    }


}
