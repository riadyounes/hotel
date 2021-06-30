package com.agendamento.hotel.unit;

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
    void store() {

        Endereco endereco = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua Fagundes Varela");
        endereco.setNumero("664");
        endereco.setComplemento("apto 01");

        Endereco resutdb = enderecoService.store(endereco);
        Assertions.assertEquals(endereco, resutdb);
    }

    @Test
    void show() {
        Endereco endereco = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua Fagundes Varela");
        endereco.setNumero("664");
        endereco.setComplemento("apto 01");

        enderecoService.store(endereco);

        Optional<Endereco> result = enderecoService.show(endereco.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index(){
        List<Endereco> before = enderecoService.index();
        
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

        enderecoService. store(endereco);
        enderecoService.store(endereco2);

        List<Endereco> after = enderecoService.index();
        Assertions.assertEquals(after.size(), before.size()+2);
    }

    @Test
    void update() {
        Endereco endereco = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua Fagundes Varela");
        endereco.setNumero("664");
        endereco.setComplemento("apto 01");

        Endereco resultdb = enderecoService.store(endereco);
        resultdb.setComplemento("ap 02");

        Endereco updateEndereco = enderecoService.update(resultdb);
        Assertions.assertEquals(resultdb.getComplemento(), updateEndereco.getComplemento());
    }

    @Test
    void destroy() {
        List<Endereco> before = enderecoService.index();
        
        Endereco endereco = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua Fagundes Varela");
        endereco.setNumero("664");
        endereco.setComplemento("apto 01");

        Endereco resultdb = enderecoService.store(endereco);

        resultdb.setId(resultdb.getId());
        enderecoService.destroy(resultdb.getId());

        List<Endereco> after = enderecoService.index();
        Assertions.assertEquals(after.size(), before.size());

    }


}
