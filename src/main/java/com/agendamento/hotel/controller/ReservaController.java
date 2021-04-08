package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.model.Hotel;
import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.model.Reserva;
import com.agendamento.hotel.service.HospedeService;
import com.agendamento.hotel.service.HotelService;
import com.agendamento.hotel.service.QuartoService;
import com.agendamento.hotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reservas")
public class ReservaController {
    private final QuartoService quartoService;
    private final HospedeService hospedeService;
    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService, HospedeService hospedeService, HotelService hotelService, QuartoService quartoService) {
        this.reservaService = reservaService;
        this.hospedeService = hospedeService;

        this.quartoService = quartoService;
    }

   @PostMapping
    public ResponseEntity<Reserva> store(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.store(reserva));
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> index(){
        return ResponseEntity.ok(reservaService.index());
    }

    @GetMapping("/hospede/{id}")
    public ResponseEntity<?> searchByHopede(@PathVariable Long id){
        Optional<Hospede> hospede = hospedeService.findOne(id);

        List<Reserva> list = reservaService.getByHospede(hospede);

        if(hospede.isPresent()){

            return new ResponseEntity<>(list,null, HttpStatus.OK);
        }
        return new ResponseEntity<>(list,null,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/quarto/{id}")
    public ResponseEntity<?> searchByQuarto(@PathVariable Long id){
        Optional<Quarto> quarto = quartoService.findOne(id);

        List<Reserva> list = reservaService.getByQuarto(quarto);

        if(quarto.isPresent()){

            return new ResponseEntity<>(list,null, HttpStatus.OK);
        }
        return new ResponseEntity<>(list,null,HttpStatus.NO_CONTENT);
    }






    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reserva>> show(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@RequestBody Reserva reserva){
        return ResponseEntity.ok(reservaService.update(reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> destroy(@PathVariable Long id){
        reservaService.destroy(id);
        return ResponseEntity.ok(null);
    }
}
