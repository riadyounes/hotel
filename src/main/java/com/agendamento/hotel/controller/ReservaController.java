package com.agendamento.hotel.controller;

import com.agendamento.hotel.enums.ReservaEstado;
import com.agendamento.hotel.model.Reserva;
import com.agendamento.hotel.service.HospedeService;
import com.agendamento.hotel.service.HotelService;
import com.agendamento.hotel.service.QuartoService;
import com.agendamento.hotel.service.ReservaService;
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
    public ResponseEntity<List<Reserva>> index(
            @RequestParam(name = "data_entrada", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data_entrada,
            @RequestParam(name = "data_saida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data_saida,
            @RequestParam(name = "estado", required = false) ReservaEstado estado,
            @RequestParam(name = "hospede_id", required = false) Long hospede_id,
            @RequestParam(name = "quarto_id", required = false) Long quarto_id) {
        if (data_entrada == null && data_saida == null && estado == null && hospede_id == null && quarto_id == null) {
            return ResponseEntity.ok(reservaService.index());
        }

        return ResponseEntity.ok(reservaService.index(data_entrada, data_saida, estado, hospede_id, quarto_id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reserva>> show(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id, @Valid @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.update(id, reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> destroy(@PathVariable Long id) {
        reservaService.destroy(id);
        return ResponseEntity.ok(null);
    }
}
