package com.agendamento.hotel;

import com.agendamento.hotel.model.Endereco;
import com.agendamento.hotel.model.Hotel;
import com.agendamento.hotel.service.HotelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class HotelTest {
    @Autowired
    private HotelService hotelService;

    @Test
    void store() {
        // Endereco endereco = new Endereco();
        // endereco.setEstado("Paraná");
        // endereco.setCidade("Foz do Iguaçu");
        // endereco.setLogradouro("Rua Fagundes Varela");
        // endereco.setNumero("664");
        // endereco.setComplemento("apto 01");

        Hotel hotel = new Hotel();
        hotel.setNome("Victor Star Hotel");
        hotel.setClassificacao((float)15);
        // hotel.setEndereco(endereco);

        Hotel result = hotelService.store(hotel);
        Assertions.assertEquals(hotel, result);
    }

    @Test
    void show() {
        // Endereco endereco = new Endereco();
        // endereco.setEstado("Paraná");
        // endereco.setCidade("Foz do Iguaçu");
        // endereco.setLogradouro("Rua Fagundes Varela");
        // endereco.setNumero("664");
        // endereco.setComplemento("apto 01");

        Hotel hotel = new Hotel();
        hotel.setNome("Victor Star Hotel");
        hotel.setClassificacao((float)15);
        // hotel.setEndereco(endereco);

        hotelService.store(hotel);

        Optional<Hotel> result = hotelService.show(hotel.getId());
        Assertions.assertTrue(result.isPresent());
    }

     @Test
     void index(){
         // Endereco endereco = new Endereco();
         // endereco.setEstado("Paraná");
         // endereco.setCidade("Foz do Iguaçu");
         // endereco.setLogradouro("Rua Fagundes Varela");
         // endereco.setNumero("664");
         // endereco.setComplemento("apto 01");

         Hotel hotel = new Hotel();
         hotel.setNome("Victor Star Hotel");
         hotel.setClassificacao((float)15);
         // hotel.setEndereco(endereco);

         Hotel hotel2 = new Hotel();
         hotel.setNome("Victor Star Hotel 2");
         hotel.setClassificacao((float)0);
         // hotel.setEndereco(endereco);

         hotelService. store(hotel);
         hotelService.store(hotel2);

         List<Hotel> list = hotelService.index();
         Assertions.assertEquals(4 , list.size());
     }

     @Test
     void update() {
         // Endereco endereco = new Endereco();
         // endereco.setEstado("Paraná");
         // endereco.setCidade("Foz do Iguaçu");
         // endereco.setLogradouro("Rua Fagundes Varela");
         // endereco.setNumero("664");
         // endereco.setComplemento("apto 01");

         Hotel hotel = new Hotel();
         hotel.setNome("Victor Star Hotel");
         hotel.setClassificacao((float)15);
         // hotel.setEndereco(endereco);

         Hotel result = hotelService.store(hotel);
         result.setNome("outro");

         Hotel update = hotelService.update(result);
         Assertions.assertEquals(result.getNome(), update.getNome());
     }

     @Test
     void destroy() {
         // Endereco endereco = new Endereco();
         // endereco.setEstado("Paraná");
         // endereco.setCidade("Foz do Iguaçu");
         // endereco.setLogradouro("Rua Fagundes Varela");
         // endereco.setNumero("664");
         // endereco.setComplemento("apto 01");

         Hotel hotel = new Hotel();
         hotel.setNome("Victor Star Hotel");
         hotel.setClassificacao((float)15);
         // hotel.setEndereco(endereco);

         Hotel result = hotelService.store(hotel);

         result.setId(result.getId());
         hotelService.destroy(result.getId());

         List<Hotel> list = hotelService.index();
         Assertions.assertEquals(0, list.size());

     }

}
