package com.agendamento.hotel.service;

import com.agendamento.hotel.model.Hotel;
import com.agendamento.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
       private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Hotel store(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public List<Hotel> index(){
        return hotelRepository.findAll();
    }

    public Optional<Hotel> show(long id){
        return  hotelRepository.findById(id);
    }

    public Hotel update(Hotel hotel) {
        Optional<Hotel> optionalHotel = this.show(hotel.getId());

        if (optionalHotel.isPresent()){
            return this.store(hotel);
        }else {
            return null;
        }
    }

    public void destroy(Long id) {
        Optional<Hotel> optionalHotel = this.show(id);

        if (optionalHotel.isPresent()){
            hotelRepository.deleteById(id);
        }
    }
}
