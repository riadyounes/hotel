package com.agendamento.hotel.unit;

import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.model.Reserva;
import com.agendamento.hotel.service.QuartoService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class QuartoTest {

    @Autowired
    private QuartoService quartoService;
    private Faker faker = new Faker(new Locale("pt-br"));

    @Test
    void store() {
        Quarto quarto = new Quarto();
        quarto.setNumero(faker.random().hex());
        quarto.setPreco(Float.parseFloat(faker.commerce().price()));
        quarto.setQuant_ocupacao(faker.number().randomDigitNotZero());
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        Quarto resultdb = quartoService.store(quarto);
        Assertions.assertEquals(quarto, resultdb);
    }

//    @Test
//    void searchByDate() {
//        List<Quarto> result = quartoService.searchByDate(
//                LocalDate.of(2020,05,29),
//                LocalDate.of(2021,05,29));
//
//        Assertions.assertEquals(0, result.size());
//    }

    @Test
    void show() {
        Quarto quarto = new Quarto();
        quarto.setNumero(faker.random().hex());
        quarto.setPreco(Float.parseFloat(faker.commerce().price()));
        quarto.setQuant_ocupacao(faker.number().randomDigitNotZero());
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        quartoService.store(quarto);

        Optional<Quarto> result = quartoService.show(quarto.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index() {

        List<Quarto> before = quartoService.index();

        Quarto quarto = new Quarto();
        quarto.setNumero(faker.random().hex());
        quarto.setPreco(Float.parseFloat(faker.commerce().price()));
        quarto.setQuant_ocupacao(faker.number().randomDigitNotZero());
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        Quarto quarto2 = new Quarto();
        quarto2.setNumero(faker.random().hex());
        quarto2.setPreco(Float.parseFloat(faker.commerce().price()));
        quarto2.setQuant_ocupacao(faker.number().randomDigitNotZero());
        quarto2.setDetalhes("Quarto para sua familia toda");

        quartoService.store(quarto);
        quartoService.store(quarto2);


        List<Quarto> after = quartoService.index();
        Assertions.assertEquals(after.size(), before.size()+2);
    }

    @Test
    void update() {
        Quarto quarto = new Quarto();
        quarto.setNumero(faker.random().hex());
        quarto.setPreco(Float.parseFloat(faker.commerce().price()));
        quarto.setQuant_ocupacao(faker.number().randomDigitNotZero());
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        Quarto resultdb = quartoService.store(quarto);

        resultdb.setDetalhes("Na verdade a suite ta ruim");
        Quarto updateQuarto = quartoService.update(resultdb);
        Assertions.assertEquals(resultdb.getDetalhes(),updateQuarto.getDetalhes());
    }

    @Test
    void destroy(){
        List<Quarto> before = quartoService.index();

        Quarto quarto = new Quarto();
        quarto.setNumero(faker.random().hex());
        quarto.setPreco(Float.parseFloat(faker.commerce().price()));
        quarto.setQuant_ocupacao(faker.number().randomDigitNotZero());
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        Quarto resultdb = quartoService.store(quarto);

        resultdb.setId(resultdb.getId());
        quartoService.destroy(resultdb.getId());


        List<Quarto> after = quartoService.index();
         Assertions.assertEquals(after.size(), before.size());


    }
}
