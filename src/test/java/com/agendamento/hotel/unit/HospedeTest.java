package com.agendamento.hotel.unit;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.service.HospedeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class HospedeTest {

    @Autowired
    private HospedeService hospedeService;

    @Test
    void store() {
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome("Riad Younes");
        hospede.setCpf("99999999999");
        hospede.setEmail("riad.younes@hotmail.com");
        hospede.setData_nascimento(date);
        hospede.setTelefone("99999-99999");
        hospede.setNacionalidade("Brasil");

        Hospede resutdb = hospedeService.store(hospede);
        Assertions.assertEquals(hospede, resutdb);
    }

    @Test
    void show() {
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome("Riad Younes");
        hospede.setCpf("99999999999");
        hospede.setEmail("riad.younes@hotmail.com");
        hospede.setData_nascimento(date);
        hospede.setTelefone("99999-99999");
        hospede.setNacionalidade("Brasil");

        hospedeService.store(hospede);

        Optional<Hospede> result = hospedeService.show(hospede.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index() {
        List<Hospede> before = hospedeService.index();
        
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome("Riad Younes");
        hospede.setCpf("99999999999");
        hospede.setEmail("riad.younes@hotmail.com");
        hospede.setData_nascimento(date);
        hospede.setTelefone("99999-99999");
        hospede.setNacionalidade("Brasil");

        LocalDate date2 = LocalDate.of(1995, 6, 29);
        Hospede hospede2 = new Hospede();
        hospede.setNome("Edinho");
        hospede.setCpf("99999999999");
        hospede.setEmail("edinhop.Hotsex.com");
        hospede.setData_nascimento(date2);
        hospede.setTelefone("99999-99999");
        hospede.setNacionalidade("Brasil");

        hospedeService.store(hospede);
        hospedeService.store(hospede2);

        List<Hospede> after = hospedeService.index();
        Assertions.assertEquals(after.size(), before.size()+2);
    }

    @Test
    void update() {
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome("Riad Younes");
        hospede.setCpf("999999999999");
        hospede.setEmail("riad.younes@hotmail.com");
        hospede.setData_nascimento(date);
        hospede.setTelefone("99999-99999");
        hospede.setNacionalidade("Brasil");

        Hospede resutdb = hospedeService.store(hospede);

        resutdb.setTelefone("999999999");
        Hospede updateHospede = hospedeService.update(resutdb);
        Assertions.assertEquals(resutdb.getTelefone(), updateHospede.getTelefone());
    }

    @Test
    void destroy() {
        List<Hospede> before = hospedeService.index();

        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome("Riad Younes");
        hospede.setCpf("999999999999");
        hospede.setEmail("riad.younes@hotmail.com");
        hospede.setData_nascimento(date);
        hospede.setTelefone("99999-99999");
        hospede.setNacionalidade("Brasil");

        Hospede resutdb = hospedeService.store(hospede);

        resutdb.setId(resutdb.getId());
        hospedeService.destroy(resutdb.getId());

        List<Hospede> after = hospedeService.index();
        Assertions.assertEquals(after.size(), before.size());
    }
}
