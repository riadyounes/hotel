package com.agendamento.hotel.unit;

import com.agendamento.hotel.enums.ReservaEstado;
import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.model.Reserva;
import com.agendamento.hotel.model.Usuario;
import com.agendamento.hotel.service.ReservaService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReservaTest {
    @Autowired
    private ReservaService reservaService;
    private final Faker faker = new Faker(new Locale("pt-br"));

    @Test
    void store() {
        Usuario usuario = new Usuario();
        usuario.setId((long) faker.number().numberBetween(1, 4));

        Hospede hospede = new Hospede();
        hospede.setId((long) faker.number().numberBetween(1, 4));

        Quarto quarto = new Quarto();
        quarto.setId((long) faker.number().numberBetween(1, 4));

        Reserva reserva = new Reserva();
        reserva.setData_entrada(LocalDate.of(2020, 5, 29));
        reserva.setData_saida(LocalDate.of(2021, 5, 29));
        reserva.setEstado(ReservaEstado.FINALIZADO);
        reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));
        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);
        reserva.setUsuario(usuario);

        Reserva result = reservaService.store(reserva);
        Assertions.assertEquals(reserva, result);
    }

//    @Test
//    void searchByDate() {
//        List<Reserva> result = reservaService.searchByDate(
//                LocalDate.of(2020,05,29),
//                LocalDate.of(2021,05,29));
//
//        Assertions.assertEquals(1, result.size());
//    }

    @Test
    void show() {
        Usuario usuario = new Usuario();
        usuario.setId((long) faker.number().numberBetween(1, 4));

        Hospede hospede = new Hospede();
        hospede.setId((long) faker.number().numberBetween(1, 4));

        Quarto quarto = new Quarto();
        quarto.setId((long) faker.number().numberBetween(1, 4));

        Reserva reserva = new Reserva();
        reserva.setData_entrada(LocalDate.of(2020, 6, 30));
        reserva.setData_saida(LocalDate.of(2021, 7, 15));
        reserva.setEstado(ReservaEstado.EM_ANDAMENTO);
        reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));
        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);
        reserva.setUsuario(usuario);

        reservaService.store(reserva);

        Optional<Reserva> result = reservaService.show(reserva.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index() {
        List<Reserva> before = reservaService.index();

        Usuario usuario = new Usuario();
        usuario.setId((long) faker.number().numberBetween(1, 4));

        Hospede hospede = new Hospede();
        hospede.setId((long) faker.number().numberBetween(1, 4));

        Quarto quarto = new Quarto();
        quarto.setId((long) faker.number().numberBetween(1, 4));

        Reserva reserva = new Reserva();
        reserva.setData_entrada(LocalDate.of(2021, 7, 20));
        reserva.setData_saida(LocalDate.of(2021, 7, 23));
        reserva.setEstado(ReservaEstado.RESERVADO);
        reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));
        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);
        reserva.setUsuario(usuario);

        reservaService.store(reserva);

        List<Reserva> after = reservaService.index();
        Assertions.assertEquals(after.size(), before.size() + 1);
    }

    @Test
    void update() {
        Usuario usuario = new Usuario();
        usuario.setId((long) faker.number().numberBetween(1, 4));

        Hospede hospede = new Hospede();
        hospede.setId((long) faker.number().numberBetween(1, 4));

        Quarto quarto = new Quarto();
        quarto.setId((long) faker.number().numberBetween(1, 4));

        Reserva reserva = new Reserva();
        reserva.setData_entrada(LocalDate.of(2020, 3, 4));
        reserva.setData_saida(LocalDate.of(2020, 3, 7));
        reserva.setEstado(ReservaEstado.CANCELADO);
        reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));
        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);
        reserva.setUsuario(usuario);

        Reserva result = reservaService.store(reserva);
        reserva.setData_saida(LocalDate.of(2020, 3, 8));

        Reserva update = reservaService.update(reserva.getId(), result);
        Assertions.assertEquals(result.getData_saida(), update.getData_saida());
    }

    @Test
    void destroy() {
        List<Reserva> before = reservaService.index();

        Usuario usuario = new Usuario();
        usuario.setId((long) faker.number().numberBetween(1, 4));

        Hospede hospede = new Hospede();
        hospede.setId((long) faker.number().numberBetween(1, 4));

        Quarto quarto = new Quarto();
        quarto.setId((long) faker.number().numberBetween(1, 4));

        Reserva reserva = new Reserva();
        reserva.setData_entrada(LocalDate.of(2020, 12, 25));
        reserva.setData_saida(LocalDate.of(2021, 1, 5));
        reserva.setEstado(ReservaEstado.RESERVADO);
        reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));
        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);
        reserva.setUsuario(usuario);

        Reserva result = reservaService.store(reserva);

        result.setId(result.getId());
        reservaService.destroy(result.getId());

        List<Reserva> after = reservaService.index();
        Assertions.assertEquals(after.size(), before.size());
    }
}
