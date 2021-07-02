package com.agendamento.hotel.unit;

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
    private Faker faker = new Faker(new Locale("pt-br"));

    @Test
    void store() {

        System.out.println(faker.name().fullName());
        Hotel hotel = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float)faker.number().numberBetween(1, 5));

        Hotel result = hotelService.store(hotel);
        Assertions.assertEquals(hotel, result);
    }

    @Test
    void show() {
        Hotel hotel = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float)faker.number().numberBetween(1, 5));

        hotelService.store(hotel);

        Optional<Hotel> result = hotelService.show(hotel.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index() {
        List<Hotel> before = hotelService.index();

        Hotel hotel = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float)faker.number().numberBetween(1, 5));

        Hotel hotel2 = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float) faker.number().numberBetween(1, 5));

        hotelService.store(hotel);
        hotelService.store(hotel2);

        List<Hotel> after = hotelService.index();
        Assertions.assertEquals(after.size(), before.size() + 2);
    }

    @Test
    void update() {
        Hotel hotel = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float) faker.number().numberBetween(1, 5));

        Hotel result = hotelService.store(hotel);
        result.setNome(faker.name().fullName());

        Hotel update = hotelService.update(result);
        Assertions.assertEquals(result.getNome(), update.getNome());
    }

    @Test
    void destroy() {
        List<Hotel> before = hotelService.index();

        Hotel hotel = new Hotel();
        hotel.setNome(faker.name().fullName());
        hotel.setClassificacao((float) faker.number().numberBetween(1, 5));

        Hotel result = hotelService.store(hotel);

        result.setId(result.getId());
        hotelService.destroy(result.getId());

        List<Hotel> after = hotelService.index();
        Assertions.assertEquals(after.size(), before.size());
    }

}
