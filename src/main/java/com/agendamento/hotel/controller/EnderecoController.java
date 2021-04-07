package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Endereco;
import com.agendamento.hotel.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<?> savedEndereco(@RequestBody Endereco endereco) throws Exception {
        try {
            Endereco savedEndereco = enderecoService.savedEndereco(endereco);
            return new ResponseEntity<>(savedEndereco, null, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {

        List<Endereco> endercoList = enderecoService.ListAllEndereco();

        if (!endercoList.isEmpty()) {
            return new ResponseEntity<>(endercoList, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(endercoList, null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Endereco>> findById(@PathVariable Long id){
        return ResponseEntity.ok(enderecoService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Endereco endereco){
        return ResponseEntity.ok(enderecoService.update(endereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@RequestBody Endereco endereco){
        enderecoService.detele(endereco.getId());
        return ResponseEntity.ok(null);
    }

}
