package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Endereco;
import com.agendamento.hotel.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> store(@Valid @RequestBody Endereco endereco) {
        return ResponseEntity.ok(enderecoService.store(endereco));
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> index() {
        return ResponseEntity.ok(enderecoService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Endereco>> show(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update(@PathVariable Long id, @Valid @RequestBody Endereco endereco) {
        Optional<Endereco> optionalEndereco = enderecoService.show(id);

        if (optionalEndereco.isPresent()) {
            endereco.setId(id);
            return ResponseEntity.ok(enderecoService.update(endereco));
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco> delete(@PathVariable Long id) {
        enderecoService.destroy(id);
        return ResponseEntity.ok(null);
    }

}
