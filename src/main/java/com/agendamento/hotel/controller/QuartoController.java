package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/quartos")
public class QuartoController {
    private final QuartoService quartoService;

    @Autowired
    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @PostMapping
    public ResponseEntity<Quarto> store(@Valid @RequestBody Quarto quarto) {
        return ResponseEntity.ok(quartoService.store(quarto));
    }

    @GetMapping
    public ResponseEntity<List<Quarto>> index(
            @RequestParam(name = "data_entrada", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data_entrada,
            @RequestParam(name = "data_saida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data_saida
    ) {
        if (data_entrada == null && data_saida == null) {
            return ResponseEntity.ok(quartoService.index());
        }

        return ResponseEntity.ok(quartoService.index(data_entrada, data_saida));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Quarto>> show(@PathVariable Long id) {
        return ResponseEntity.ok(quartoService.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quarto> update(@PathVariable Long id, @Valid @RequestBody Quarto quarto) {
        Optional<Quarto> optionalQuarto = quartoService.show(id);

        if (optionalQuarto.isPresent()) {
            quarto.setId(id);
            return ResponseEntity.ok(quartoService.update(quarto));
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quarto> delete(@PathVariable Long id) {
        quartoService.destroy(id);
        return ResponseEntity.ok(null);
    }

}
