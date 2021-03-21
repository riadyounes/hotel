package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.service.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospede")
public class HospedeController {
    private final HospedeService hospedeService;

    @Autowired
    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @PostMapping
    public ResponseEntity<?> cadastraHospede(@RequestBody Hospede hospede) throws Exception {
        try {
            Hospede hospedeSaved = hospedeService.cadastraHospede(hospede);
            return new ResponseEntity<>(hospedeSaved, null, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {

        List<Hospede> hospedeList = hospedeService.ListAllHospede();

        if (!hospedeList.isEmpty()) {
            return new ResponseEntity<>(hospedeList, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(hospedeList, null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Hospede>> findById(@PathVariable Long id){
        return ResponseEntity.ok(hospedeService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Hospede hospede){
        return ResponseEntity.ok(hospedeService.update(hospede));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@RequestBody Hospede hospede){
        return ResponseEntity.ok(null);
    }

}
