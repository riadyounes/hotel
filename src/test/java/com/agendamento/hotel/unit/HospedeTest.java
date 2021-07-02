package com.agendamento.hotel.unit;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.service.HospedeService;
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
public class HospedeTest {

    @Autowired
    private HospedeService hospedeService;
    private Faker faker = new Faker(new Locale("pt-br"));

    @Test
    void store() {
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome(faker.name().name());
        hospede.setCpf(faker.idNumber().valid());
        hospede.setEmail(faker.internet().emailAddress());
        hospede.setData_nascimento(date);
        hospede.setTelefone(faker.phoneNumber().cellPhone());
        hospede.setNacionalidade(faker.nation().nationality());

        Hospede resutdb = hospedeService.store(hospede);
        Assertions.assertEquals(hospede, resutdb);
    }

    @Test
    void show() {
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome(faker.name().name());
        hospede.setCpf(faker.idNumber().valid());
        hospede.setEmail(faker.internet().emailAddress());
        hospede.setData_nascimento(date);
        hospede.setTelefone(faker.phoneNumber().cellPhone());
        hospede.setNacionalidade(faker.nation().nationality());

        hospedeService.store(hospede);

        Optional<Hospede> result = hospedeService.show(hospede.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index() {
        List<Hospede> before = hospedeService.index();
        
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome(faker.name().name());
        hospede.setCpf(faker.idNumber().valid());
        hospede.setEmail(faker.internet().emailAddress());
        hospede.setData_nascimento(date);
        hospede.setTelefone(faker.phoneNumber().cellPhone());
        hospede.setNacionalidade(faker.nation().nationality());

        LocalDate date2 = LocalDate.of(1995, 6, 29);
        Hospede hospede2 = new Hospede();
        hospede.setNome(faker.name().name());
        hospede.setCpf(faker.idNumber().valid());
        hospede.setEmail(faker.internet().emailAddress());
        hospede.setData_nascimento(date2);
        hospede.setTelefone(faker.phoneNumber().cellPhone());
        hospede.setNacionalidade(faker.nation().nationality());

        hospedeService.store(hospede);
        hospedeService.store(hospede2);

        List<Hospede> after = hospedeService.index();
        Assertions.assertEquals(after.size(), before.size()+2);
    }

    @Test
    void update() {
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome(faker.name().name());
        hospede.setCpf(faker.idNumber().valid());
        hospede.setEmail(faker.internet().emailAddress());
        hospede.setData_nascimento(date);
        hospede.setTelefone(faker.phoneNumber().cellPhone());
        hospede.setNacionalidade(faker.nation().nationality());

        Hospede resutdb = hospedeService.store(hospede);

        resutdb.setTelefone(faker.phoneNumber().cellPhone());
        Hospede updateHospede = hospedeService.update(resutdb);
        Assertions.assertEquals(resutdb.getTelefone(), updateHospede.getTelefone());
    }

    @Test
    void destroy() {
        List<Hospede> before = hospedeService.index();

        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome(faker.name().name());
        hospede.setCpf(faker.idNumber().valid());
        hospede.setEmail(faker.internet().emailAddress());
        hospede.setData_nascimento(date);
        hospede.setTelefone(faker.phoneNumber().cellPhone());
        hospede.setNacionalidade(faker.nation().nationality());

        Hospede resutdb = hospedeService.store(hospede);

        resutdb.setId(resutdb.getId());
        hospedeService.destroy(resutdb.getId());

        List<Hospede> after = hospedeService.index();
        Assertions.assertEquals(after.size(), before.size());
    }
}
