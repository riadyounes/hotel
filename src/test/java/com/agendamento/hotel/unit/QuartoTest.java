package com.agendamento.hotel.unit;

import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.model.Reserva;
import com.agendamento.hotel.service.QuartoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QuartoTest {

    @Autowired
    private QuartoService quartoService;

    @Test
    void store() {
        Quarto quarto = new Quarto();
        quarto.setNumero("666");
        quarto.setPreco((float) 299.99);
        quarto.setQuant_ocupacao(2);
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
        quarto.setNumero("666");
        quarto.setPreco((float) 299.99);
        quarto.setQuant_ocupacao(2);
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        quartoService.store(quarto);

        Optional<Quarto> result = quartoService.show(quarto.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index() {

        List<Quarto> before = quartoService.index();

        Quarto quarto = new Quarto();
        quarto.setNumero("666");
        quarto.setPreco((float) 299.99);
        quarto.setQuant_ocupacao(2);
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        Quarto quarto2 = new Quarto();
        quarto2.setNumero("999");
        quarto2.setPreco((float) 999.99);
        quarto2.setQuant_ocupacao(5);
        quarto2.setDetalhes("Quarto para sua familia toda");

        quartoService.store(quarto);
        quartoService.store(quarto2);


        List<Quarto> after = quartoService.index();
        Assertions.assertEquals(after.size(), before.size()+2);
    }

    @Test
    void update() {
        Quarto quarto = new Quarto();
        quarto.setNumero("666");
        quarto.setPreco((float) 299.99);
        quarto.setQuant_ocupacao(2);
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
        quarto.setNumero("666");
        quarto.setPreco((float) 299.99);
        quarto.setQuant_ocupacao(2);
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        Quarto resultdb = quartoService.store(quarto);

        resultdb.setId(resultdb.getId());
        quartoService.destroy(resultdb.getId());


        List<Quarto> after = quartoService.index();
         Assertions.assertEquals(after.size(), before.size());


    }
}
