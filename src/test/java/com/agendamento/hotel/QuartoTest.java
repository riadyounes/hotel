package com.agendamento.hotel;

import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.service.QuartoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QuartoTest {

    @Autowired
    private QuartoService quartoService;

    @Test
    void saveQuarto() {
        Quarto quarto = new Quarto();
        quarto.setNumero("666");
        quarto.setPreco((float) 299.99);
        quarto.setQuant_ocupacao(2);
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        Quarto resultdb = quartoService.saveQuarto(quarto);
        Assertions.assertEquals(quarto, resultdb);
    }

    @Test
    void getById() {
        Quarto quarto = new Quarto();
        quarto.setNumero("666");
        quarto.setPreco((float) 299.99);
        quarto.setQuant_ocupacao(2);
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        quartoService.saveQuarto(quarto);

        Optional<Quarto> result = quartoService.findOne(quarto.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void listAllQuarto() {
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

        quartoService.saveQuarto(quarto);
        quartoService.saveQuarto(quarto2);

        List<Quarto> lista = quartoService.listAllQuarto();
        Assertions.assertEquals(2, lista.size());
    }

    @Test
    void editQuarto() {
        Quarto quarto = new Quarto();
        quarto.setNumero("666");
        quarto.setPreco((float) 299.99);
        quarto.setQuant_ocupacao(2);
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        Quarto resultdb = quartoService.saveQuarto(quarto);

        resultdb.setDetalhes("Na verdade a suite ta ruim");
        Quarto updateQuarto = quartoService.update(resultdb);
        Assertions.assertEquals(resultdb.getDetalhes(),updateQuarto.getDetalhes());
    }

    @Test
    void deleteQuarto(){
        Quarto quarto = new Quarto();
        quarto.setNumero("666");
        quarto.setPreco((float) 299.99);
        quarto.setQuant_ocupacao(2);
        quarto.setDetalhes("Quarto para duas pessoas com suite master");

        Quarto resultdb = quartoService.saveQuarto(quarto);

        resultdb.setId(resultdb.getId());
        quartoService.delete(resultdb.getId());

        List<Quarto> list = quartoService.listAllQuarto();
        Assertions.assertEquals(0,list.size());

    }
}
