package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.model.Reserva;
import com.agendamento.hotel.service.HospedeService;
import com.agendamento.hotel.service.HotelService;
import com.agendamento.hotel.service.QuartoService;
import com.agendamento.hotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
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
    public ResponseEntity<Reserva> store(@Valid @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.store(reserva));
    }

    @PostMapping("{id}/finalizar")
    public ResponseEntity<Reserva> finalizarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.finalizar(id));
    }

    @PostMapping("{id}/em_andamento")
    public ResponseEntity<Reserva> emAndamentoReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.emAndamento(id));
    }

    @PostMapping("{id}/cancelar")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelar(id));
    }


    @GetMapping
    public ResponseEntity<List<Reserva>> index() {
        return ResponseEntity.ok(reservaService.index());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Reserva>> searchByDate(
            @RequestParam("data_entrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data_entrada,
            @RequestParam("data_saida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data_saida) {
        return ResponseEntity.ok(reservaService.searchByDate(data_entrada, data_saida));
    }

    @GetMapping("/hospede/{id}")
    public ResponseEntity<?> searchByHopede(@PathVariable Long id) {
        Optional<Hospede> hospede = hospedeService.show(id);

        List<Reserva> list = reservaService.getByHospede(hospede);

        if (hospede.isPresent()) {

            return new ResponseEntity<>(list, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/quarto/{id}")
//    public ResponseEntity<Reserva> searchByQuarto(@PathVariable Long id) {
//        Optional<Quarto> quarto = quartoService.show(id);
//
//        List<Reserva> list = reservaService.getByQuarto(quarto);
//
//        if (quarto.isPresent()) {
//
//            return new ResponseEntity<>(list, null, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reserva>> show(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id, @Valid @RequestBody Reserva reserva) {
        Optional<Reserva> optionalReserva = reservaService.show(id);

        if (optionalReserva.isPresent()) {
            return ResponseEntity.ok(reservaService.update(reserva));
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> destroy(@PathVariable Long id) {
        reservaService.destroy(id);
        return ResponseEntity.ok(null);
    }
}
