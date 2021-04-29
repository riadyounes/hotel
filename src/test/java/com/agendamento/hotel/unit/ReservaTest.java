package com.agendamento.hotel.unit;

import com.agendamento.hotel.model.*;
import com.agendamento.hotel.service.ReservaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReservaTest {
    @Autowired
    private ReservaService reservaService;

    @Test
    void store() {
        Reserva reserva = new Reserva();
        reserva.setData_entrada(LocalDate.of(2020, 5, 29));
        reserva.setData_saida(LocalDate.of(2021, 5, 29));
        reserva.setEstado(EnumEstado.RESERVADO);
        reserva.setPreco_total((float) 15);

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
        reserva.setData_entrada(LocalDate.of(2020, 5, 29));
        reserva.setData_saida(LocalDate.of(2021, 5, 29));
        reserva.setEstado(EnumEstado.RESERVADO);
        reserva.setPreco_total((float) 15);

        reservaService.store(reserva);

        Optional<Reserva> result = reservaService.show(reserva.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index() {
        List<Reserva> before = reservaService.index();

        Reserva reserva = new Reserva();
        reserva.setData_entrada(LocalDate.of(2020, 5, 29));
        reserva.setData_saida(LocalDate.of(2021, 5, 29));
        reserva.setEstado(EnumEstado.RESERVADO);
        reserva.setPreco_total((float) 15);

        Reserva reserva2 = new Reserva();
        reserva.setData_entrada(LocalDate.of(2021, 5, 29));
        reserva.setData_saida(LocalDate.of(2023, 5, 29));
        reserva.setEstado(EnumEstado.RESERVADO);
        reserva.setPreco_total((float) 15);

        reservaService.store(reserva);
        reservaService.store(reserva2);

        List<Reserva> after = reservaService.index();
        Assertions.assertEquals(after.size(), before.size() + 2);
    }

      @Test
      void update() {
          Reserva reserva = new Reserva();
          reserva.setData_entrada(LocalDate.of(2020, 5, 29));
          reserva.setData_saida(LocalDate.of(2021, 5, 29));
          reserva.setEstado(EnumEstado.RESERVADO);
          reserva.setPreco_total((float) 15);

         Reserva result = reservaService.store(reserva);
          reserva.setData_saida(LocalDate.of(2020, 8, 29));

          Reserva update = reservaService.update(result);
          Assertions.assertEquals(result.getData_saida(), update.getData_saida());
      }

      @Test
      void destroy() {
          List<Reserva> before = reservaService.index();

          Reserva reserva = new Reserva();
          reserva.setData_entrada(LocalDate.of(2020, 5, 29));
          reserva.setData_saida(LocalDate.of(2021, 5, 29));
          reserva.setEstado(EnumEstado.RESERVADO);
          reserva.setPreco_total((float) 15);

         Reserva result = reservaService.store(reserva);

          result.setId(result.getId());
          reservaService.destroy(result.getId());

          List<Reserva> after = reservaService.index();
          Assertions.assertEquals(after.size(), before.size());
      }
}
