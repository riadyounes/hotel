package com.agendamento.hotel.unit;

import com.agendamento.hotel.enums.ReservaEstado;
import com.agendamento.hotel.model.Reserva;
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
        Reserva reserva = new Reserva();
        reserva.setData_entrada(LocalDate.of(2020, 5, 29));
        reserva.setData_saida(LocalDate.of(2021, 5, 29));
        reserva.setEstado(ReservaEstado.FINALIZADO);
        reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));

        Reserva result = reservaService.store(reserva);
        Assertions.assertEquals(reserva, result);
    }

    @Test
    void searchByDate() {
        List<Reserva> result = reservaService.searchByDate(
                LocalDate.of(2020,05,29),
                LocalDate.of(2021,05,29));

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void show() {
        Reserva reserva = new Reserva();
        reserva.setData_entrada(LocalDate.of(2020, 6, 30));
        reserva.setData_saida(LocalDate.of(2021, 7, 15));
        reserva.setEstado(ReservaEstado.EM_ANDAMENTO);
        reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));

        reservaService.store(reserva);

        Optional<Reserva> result = reservaService.show(reserva.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index() {
        List<Reserva> before = reservaService.index();

        Reserva reserva = new Reserva();
        reserva.setData_entrada(LocalDate.of(2021, 7, 20));
        reserva.setData_saida(LocalDate.of(2021, 7, 23));
        reserva.setEstado(ReservaEstado.RESERVADO);
        reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));

        Reserva reserva2 = new Reserva();
        reserva.setData_entrada(LocalDate.of(2021, 5, 1));
        reserva.setData_saida(LocalDate.of(2023, 5, 3));
        reserva.setEstado(ReservaEstado.EM_ANDAMENTO);
        reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));

        reservaService.store(reserva);
        reservaService.store(reserva2);

        List<Reserva> after = reservaService.index();
        Assertions.assertEquals(after.size(), before.size() + 2);
    }

      @Test
      void update() {
          Reserva reserva = new Reserva();
          reserva.setData_entrada(LocalDate.of(2020, 3, 4));
          reserva.setData_saida(LocalDate.of(2020, 3, 7));
          reserva.setEstado(ReservaEstado.CANCELADO);
          reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));

         Reserva result = reservaService.store(reserva);
          reserva.setData_saida(LocalDate.of(2020, 3, 8));

          Reserva update = reservaService.update(result);
          Assertions.assertEquals(result.getData_saida(), update.getData_saida());
      }

      @Test
      void destroy() {
          List<Reserva> before = reservaService.index();

          Reserva reserva = new Reserva();
          reserva.setData_entrada(LocalDate.of(2020, 12, 25));
          reserva.setData_saida(LocalDate.of(2021, 1, 5));
          reserva.setEstado(ReservaEstado.RESERVADO);
          reserva.setPreco_total(Float.parseFloat(faker.commerce().price()));

         Reserva result = reservaService.store(reserva);

          result.setId(result.getId());
          reservaService.destroy(result.getId());

          List<Reserva> after = reservaService.index();
          Assertions.assertEquals(after.size(), before.size());
      }
}
