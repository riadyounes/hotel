package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Reserva;
import com.agendamento.hotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reservas")
public class ReservaController {  private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

   @PostMapping
    public ResponseEntity<Reserva> store(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.store(reserva));
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> index(){
        return ResponseEntity.ok(reservaService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reserva>> show(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id,@RequestBody Reserva reserva){
        Optional<Reserva> optionalReserva = reservaService.show(id);

        if (optionalReserva.isPresent()){
            return ResponseEntity.ok(reservaService.update(reserva));
        }else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> destroy(@PathVariable Long id){
        reservaService.destroy(id);
        return ResponseEntity.ok(null);
    }
}
