package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Hotel;
import com.agendamento.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/hoteis")
public class HotelController {
     private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

   @PostMapping
    public ResponseEntity<Hotel> store(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.store(hotel));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> index(){
        return ResponseEntity.ok(hotelService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Hotel>> show(@PathVariable Long id){
        return ResponseEntity.ok(hotelService.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(@RequestBody Hotel hotel){
        return ResponseEntity.ok(hotelService.update(hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Hotel> destroy(@PathVariable Long id){
        hotelService.destroy(id);
        return ResponseEntity.ok(null);
    }
}
