package com.agendamento.hotel.unit;

import com.agendamento.hotel.model.Endereco;
import com.agendamento.hotel.service.EnderecoService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EnderecoTest {

    @Autowired
    private EnderecoService enderecoService;
    private Faker faker = new Faker(new Locale("pt-br"));

    @Test
    void store() {

        Endereco endereco = new Endereco();
        endereco.setEstado(faker.address().state());
        endereco.setCidade(faker.address().city());
        endereco.setLogradouro(faker.address().streetName());
        endereco.setNumero(faker.address().buildingNumber());
        endereco.setComplemento(faker.address().secondaryAddress());

        Endereco resutdb = enderecoService.store(endereco);
        Assertions.assertEquals(endereco, resutdb);
    }

    @Test
    void show() {
        Endereco endereco = new Endereco();
        endereco.setEstado(faker.address().state());
        endereco.setCidade(faker.address().city());
        endereco.setLogradouro(faker.address().streetName());
        endereco.setNumero(faker.address().buildingNumber());
        endereco.setComplemento(faker.address().secondaryAddress());

        enderecoService.store(endereco);

        Optional<Endereco> result = enderecoService.show(endereco.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index() {
        List<Endereco> before = enderecoService.index();

        Endereco endereco = new Endereco();
        endereco.setEstado(faker.address().state());
        endereco.setCidade(faker.address().city());
        endereco.setLogradouro(faker.address().streetName());
        endereco.setNumero(faker.address().buildingNumber());
        endereco.setComplemento(faker.address().secondaryAddress());

        Endereco endereco2 = new Endereco();
        endereco.setEstado(faker.address().state());
        endereco.setCidade(faker.address().city());
        endereco.setLogradouro(faker.address().streetName());
        endereco.setNumero(faker.address().buildingNumber());
        endereco.setComplemento(faker.address().secondaryAddress());

        enderecoService.store(endereco);
        enderecoService.store(endereco2);

        List<Endereco> after = enderecoService.index();
        Assertions.assertEquals(after.size(), before.size() + 2);
    }

    @Test
    void update() {
        Endereco endereco = new Endereco();
        endereco.setEstado(faker.address().state());
        endereco.setCidade(faker.address().city());
        endereco.setLogradouro(faker.address().streetName());
        endereco.setNumero(faker.address().buildingNumber());
        endereco.setComplemento(faker.address().secondaryAddress());

        Endereco resultdb = enderecoService.store(endereco);
        resultdb.setComplemento(faker.address().secondaryAddress());

        Endereco updateEndereco = enderecoService.update(resultdb);
        Assertions.assertEquals(resultdb.getComplemento(), updateEndereco.getComplemento());
    }

    @Test
    void destroy() {
        List<Endereco> before = enderecoService.index();

        Endereco endereco = new Endereco();
        endereco.setEstado(faker.address().state());
        endereco.setCidade(faker.address().city());
        endereco.setLogradouro(faker.address().streetName());
        endereco.setNumero(faker.address().buildingNumber());
        endereco.setComplemento(faker.address().secondaryAddress());

        Endereco resultdb = enderecoService.store(endereco);

        resultdb.setId(resultdb.getId());
        enderecoService.destroy(resultdb.getId());

        List<Endereco> after = enderecoService.index();
        Assertions.assertEquals(after.size(), before.size());

    }


}
