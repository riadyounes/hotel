package com.agendamento.hotel;

import com.agendamento.hotel.model.Endereco;
import com.agendamento.hotel.service.EnderecoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EnderecoTest {

    @Autowired
    private EnderecoService enderecoService;

    @Test
    void savedEndereco() {

        Endereco endereco = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua Fagundes Varela");
        endereco.setNumero("664");
        endereco.setComplemento("apto 01");

        Endereco resutdb = enderecoService.savedEndereco(endereco);
        Assertions.assertEquals(endereco, resutdb);
    }

    @Test
    void getById() {
        Endereco endereco = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua Fagundes Varela");
        endereco.setNumero("664");
        endereco.setComplemento("apto 01");

        enderecoService.savedEndereco(endereco);

        Optional<Endereco> result = enderecoService.findOne(endereco.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void listAllEndereco(){
        Endereco endereco = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua Fagundes Varela");
        endereco.setNumero("664");
        endereco.setComplemento("apto 01");

        Endereco endereco2 = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua das Missões");
        endereco.setNumero("554");
        endereco.setComplemento("apto 01");

        enderecoService. savedEndereco(endereco);
        enderecoService.savedEndereco(endereco2);

        List<Endereco> list = enderecoService.ListAllEndereco();
        Assertions.assertEquals(4 , list.size());
    }

    @Test
    void editEndereco() {
        Endereco endereco = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua Fagundes Varela");
        endereco.setNumero("664");
        endereco.setComplemento("apto 01");

        Endereco resultdb = enderecoService.savedEndereco(endereco);
        resultdb.setComplemento("ap 02");

        Endereco updateEndereco = enderecoService.update(resultdb);
        Assertions.assertEquals(resultdb.getComplemento(), updateEndereco.getComplemento());
    }

    @Test
    void deleteEndereco() {
        List<Endereco> before = enderecoService.ListAllEndereco();
        
        Endereco endereco = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua Fagundes Varela");
        endereco.setNumero("664");
        endereco.setComplemento("apto 01");

        Endereco resultdb = enderecoService.savedEndereco(endereco);

        resultdb.setId(resultdb.getId());
        enderecoService.detele(resultdb.getId());

        List<Endereco> after = enderecoService.ListAllEndereco();
        Assertions.assertEquals(after.size(), before.size());

    }


}
