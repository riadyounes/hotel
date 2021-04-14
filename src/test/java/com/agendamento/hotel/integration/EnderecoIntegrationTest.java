package com.agendamento.hotel.integration;

import com.agendamento.hotel.model.Endereco;
import com.agendamento.hotel.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EnderecoService enderecoService;

    @Test
    void insereEndereco_Test() throws Exception {

        Endereco endereco = new Endereco();
        endereco.setEstado("Paraná");
        endereco.setCidade("Foz do Iguaçu");
        endereco.setLogradouro("Rua Fagundes Varela");
        endereco.setNumero("664");
        endereco.setComplemento("apto 01");

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/enderecos")
                .content(objectMapper.writeValueAsString(endereco))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void listaTodosEndereco_Test() throws Exception {

//        Endereco endereco = new Endereco();
//        endereco.setEstado("Paraná");
//        endereco.setCidade("Foz do Iguaçu");
//        endereco.setLogradouro("Rua Fagundes Varela");
//        endereco.setNumero("664");
//        endereco.setComplemento("apto 01");
//        enderecoService.savedEndereco(endereco);


        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/enderecos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    void deleteEndereco() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/enderecos/{id}", 1))
//                .andExpect(status().isAccepted());
//    }







}
