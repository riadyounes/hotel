package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/quartos")
public class QuartoController {
    private final QuartoService quartoService;

    @Autowired
    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @PostMapping
    public ResponseEntity<?> saveQuarto(@RequestBody Quarto quarto) throws Exception {
        try {
            Quarto quartoSaved = quartoService.saveQuarto(quarto);
            return new ResponseEntity<>(quartoSaved, null, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        List<Quarto> quartoList = quartoService.listAllQuarto();
        if (!quartoList.isEmpty()) {
            return new ResponseEntity<>(quartoList, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(quartoList, null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Quarto>> findById(@PathVariable Long id){
        return ResponseEntity.ok(quartoService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Quarto quarto){
        return ResponseEntity.ok(quartoService.update(quarto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@RequestBody Quarto quarto){
        return ResponseEntity.ok(null);
    }

}
