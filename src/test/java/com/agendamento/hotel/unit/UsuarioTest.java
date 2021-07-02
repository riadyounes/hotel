package com.agendamento.hotel.unit;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.model.Usuario;
import com.agendamento.hotel.service.UsuarioService;
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
public class UsuarioTest {

    @Autowired
    private UsuarioService usuarioService;
    private Faker faker = new Faker(new Locale("pt-br"));

    @Test
    void store(){

        Usuario usuario = new Usuario();
        usuario.setNome(faker.name().name());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setSenha(faker.random().hex());

        Usuario resultdb = usuarioService.store(usuario);
        Assertions.assertEquals(usuario, resultdb);
    }

    @Test
    void show(){

        Usuario usuario = new Usuario();
        usuario.setNome(faker.name().name());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setSenha(faker.random().hex());

        usuarioService.store(usuario);

        Optional<Usuario> result = usuarioService.show(usuario.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index(){

        List<Usuario> before = usuarioService.index();


        Usuario usuario = new Usuario();
        usuario.setNome(faker.name().name());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setSenha(faker.random().hex());

        Usuario usuario2 = new Usuario();
        usuario2.setNome(faker.name().name());
        usuario2.setEmail(faker.internet().emailAddress());
        usuario2.setSenha(faker.random().hex());

        usuarioService.store(usuario);
        usuarioService.store(usuario2);

        List<Usuario> after = usuarioService.index();
        Assertions.assertEquals(after.size(), before.size()+2);

    }

    @Test
    void update(){

        Usuario usuario = new Usuario();
        usuario.setNome(faker.name().name());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setSenha(faker.random().hex());

        Usuario resultdb = usuarioService.store(usuario);

        resultdb.setNome(faker.name().name());
        Usuario updateUsuario = usuarioService.update(resultdb);
        Assertions.assertEquals(resultdb.getNome(),updateUsuario.getNome());
    }

    @Test
    void destroy(){

        List<Usuario> before = usuarioService.index();

        Usuario usuario = new Usuario();
        usuario.setNome(faker.name().name());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setSenha(faker.random().hex());

        Usuario resultdb = usuarioService.store(usuario);

        resultdb.setId(resultdb.getId());
        usuarioService.destroy(resultdb.getId());


        List<Usuario> after = usuarioService.index();
         Assertions.assertEquals(after.size(), before.size());

    }
}
