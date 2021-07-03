package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.service.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/hospedes")
public class HospedeController {
    private final HospedeService hospedeService;

    @Autowired
    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @PostMapping
    public ResponseEntity<Hospede> store(@Valid @RequestBody Hospede hospede) {
        return ResponseEntity.ok(hospedeService.store(hospede));
    }

    @GetMapping
    public ResponseEntity<List<Hospede>> index() {
        return ResponseEntity.ok(hospedeService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Hospede>> show(@PathVariable Long id) {
        return ResponseEntity.ok(hospedeService.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospede> update(@PathVariable Long id, @Valid @RequestBody Hospede hospede) {
        Optional<Hospede> optionalHospede = hospedeService.show(id);

        if (optionalHospede.isPresent()) {
            hospede.setId(id);
            return ResponseEntity.ok(hospedeService.update(hospede));
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Hospede> delete(@PathVariable Long id) {
        hospedeService.destroy(id);
        return ResponseEntity.ok(null);
    }

}
