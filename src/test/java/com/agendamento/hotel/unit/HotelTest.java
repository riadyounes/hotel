package com.agendamento.hotel.unit;

import com.agendamento.hotel.model.Endereco;
import com.agendamento.hotel.model.Hotel;
import com.agendamento.hotel.service.HotelService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class HotelTest {
    @Autowired
    private HotelService hotelService;
    private final Faker faker = new Faker(new Locale("pt-br"));

    @Test
    void store() {
        Endereco endereco = new Endereco();
        endereco.setId((long) faker.number().numberBetween(1, 4));

        System.out.println(faker.name().fullName());
        Hotel hotel = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float) faker.number().numberBetween(1, 5));
        hotel.setEndereco(endereco);

        Hotel result = hotelService.store(hotel);
        Assertions.assertEquals(hotel, result);
    }

    @Test
    void show() {
        Endereco endereco = new Endereco();
        endereco.setId((long) faker.number().numberBetween(1, 4));

        Hotel hotel = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float) faker.number().numberBetween(1, 5));
        hotel.setEndereco(endereco);

        hotelService.store(hotel);

        Optional<Hotel> result = hotelService.show(hotel.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index() {
        List<Hotel> before = hotelService.index();

        Endereco endereco = new Endereco();
        endereco.setId((long) faker.number().numberBetween(1, 4));

        Hotel hotel = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float) faker.number().numberBetween(1, 5));
        hotel.setEndereco(endereco);

        hotelService.store(hotel);

        List<Hotel> after = hotelService.index();
        Assertions.assertEquals(after.size(), before.size() + 1);
    }

    @Test
    void update() {
        Endereco endereco = new Endereco();
        endereco.setId((long) faker.number().numberBetween(1, 4));

        Hotel hotel = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float) faker.number().numberBetween(1, 5));
        hotel.setEndereco(endereco);

        Hotel result = hotelService.store(hotel);
        result.setNome(faker.name().fullName());

        Hotel update = hotelService.update(result);
        Assertions.assertEquals(result.getNome(), update.getNome());
    }

    @Test
    void destroy() {
        List<Hotel> before = hotelService.index();

        Endereco endereco = new Endereco();
        endereco.setId((long) faker.number().numberBetween(1, 4));

        Hotel hotel = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float) faker.number().numberBetween(1, 5));
        hotel.setEndereco(endereco);

        Hotel result = hotelService.store(hotel);

        result.setId(result.getId());
        hotelService.destroy(result.getId());

        List<Hotel> after = hotelService.index();
        Assertions.assertEquals(after.size(), before.size());
    }

}
