package com.agendamento.hotel.unit;

import com.agendamento.hotel.model.Hospede;
import com.agendamento.hotel.model.Quarto;
import com.agendamento.hotel.model.Usuario;
import com.agendamento.hotel.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    void store(){

        Usuario usuario = new Usuario();
        usuario.setNome("edinho");
        usuario.setEmail("edinho.pereira95@gmail.com");
        usuario.setSenha("123456");

        Usuario resultdb = usuarioService.store(usuario);
        Assertions.assertEquals(usuario, resultdb);
    }

    @Test
    void show(){

        Usuario usuario = new Usuario();
        usuario.setNome("edinho");
        usuario.setEmail("edinho.pereira95@gmail.com");
        usuario.setSenha("123456");

        usuarioService.store(usuario);

        Optional<Usuario> result = usuarioService.show(usuario.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void index(){

        List<Usuario> before = usuarioService.index();


        Usuario usuario = new Usuario();
        usuario.setNome("edinho");
        usuario.setEmail("edinho.pereira95@gmail.com");
        usuario.setSenha("123456");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("lio skrr");
        usuario2.setEmail("lio.crooncs@gmail.com");
        usuario2.setSenha("123456");

        usuarioService.store(usuario);
        usuarioService.store(usuario2);

        List<Usuario> after = usuarioService.index();
        Assertions.assertEquals(after.size(), before.size()+2);

    }

    @Test
    void update(){

        Usuario usuario = new Usuario();
        usuario.setNome("edinho");
        usuario.setEmail("edinho.pereira95@gmail.com");
        usuario.setSenha("123456");

        Usuario resultdb = usuarioService.store(usuario);

        resultdb.setNome("Edson Pereira");
        Usuario updateUsuario = usuarioService.update(resultdb);
        Assertions.assertEquals(resultdb.getNome(),updateUsuario.getNome());
    }

    @Test
    void destroy(){

        List<Usuario> before = usuarioService.index();

        Usuario usuario = new Usuario();
        usuario.setNome("edinho");
        usuario.setEmail("edinho.pereira95@gmail.com");
        usuario.setSenha("123456");

        Usuario resultdb = usuarioService.store(usuario);

        resultdb.setId(resultdb.getId());
        usuarioService.destroy(resultdb.getId());


        List<Usuario> after = usuarioService.index();
         Assertions.assertEquals(after.size(), before.size());

    }
}
