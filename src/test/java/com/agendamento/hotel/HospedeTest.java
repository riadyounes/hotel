package com.agendamento.hotel;

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
    void cadastraHospede() {
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome("Riad Younes");
        hospede.setCpf("99999999999");
        hospede.setEmail("riad.younes@hotmail.com");
        hospede.setData_nascimento(date);
        hospede.setTelefone("99999-99999");
        hospede.setNacionalidade("Brasil");

        Hospede resutdb = hospedeService.cadastraHospede(hospede);
        Assertions.assertEquals(hospede, resutdb);
    }

    @Test
    void getById() {
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome("Riad Younes");
        hospede.setCpf("99999999999");
        hospede.setEmail("riad.younes@hotmail.com");
        hospede.setData_nascimento(date);
        hospede.setTelefone("99999-99999");
        hospede.setNacionalidade("Brasil");

        hospedeService.cadastraHospede(hospede);

        Optional<Hospede> result = hospedeService.findOne(hospede.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void ListAllHospede() {
        List<Hospede> before = hospedeService.ListAllHospede();
        
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

        hospedeService.cadastraHospede(hospede);
        hospedeService.cadastraHospede(hospede2);

        List<Hospede> after = hospedeService.ListAllHospede();
        Assertions.assertEquals(after.size(), before.size()+2);
    }

    @Test
    void EditHospede() {
        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome("Riad Younes");
        hospede.setCpf("999999999999");
        hospede.setEmail("riad.younes@hotmail.com");
        hospede.setData_nascimento(date);
        hospede.setTelefone("99999-99999");
        hospede.setNacionalidade("Brasil");

        Hospede resutdb = hospedeService.cadastraHospede(hospede);

        resutdb.setTelefone("999999999");
        Hospede updateHospede = hospedeService.update(resutdb);
        Assertions.assertEquals(resutdb.getTelefone(), updateHospede.getTelefone());
    }

    @Test
    void ExcluirHospede() {
        List<Hospede> before = hospedeService.ListAllHospede();

        LocalDate date = LocalDate.of(2000, 5, 29);
        Hospede hospede = new Hospede();
        hospede.setNome("Riad Younes");
        hospede.setCpf("999999999999");
        hospede.setEmail("riad.younes@hotmail.com");
        hospede.setData_nascimento(date);
        hospede.setTelefone("99999-99999");
        hospede.setNacionalidade("Brasil");

        Hospede resutdb = hospedeService.cadastraHospede(hospede);

        resutdb.setId(resutdb.getId());
        hospedeService.delete(resutdb.getId());

        List<Hospede> after = hospedeService.ListAllHospede();
        Assertions.assertEquals(after.size(), before.size());
    }
}
