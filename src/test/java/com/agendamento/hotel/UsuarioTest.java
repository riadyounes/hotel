package com.agendamento.hotel;

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
    void saveUsuario(){

        Usuario usuario = new Usuario();
        usuario.setNome("edinho");
        usuario.setEmail("edinho.pereira95@gmail.com");
        usuario.setSenha("123456");

        Usuario resultdb = usuarioService.saveUsuario(usuario);
        Assertions.assertEquals(usuario, resultdb);
    }

    @Test
    void getById(){

        Usuario usuario = new Usuario();
        usuario.setNome("edinho");
        usuario.setEmail("edinho.pereira95@gmail.com");
        usuario.setSenha("123456");

        usuarioService.saveUsuario(usuario);

        Optional<Usuario> result = usuarioService.findOne(usuario.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void listAllUsuario(){
        List<Usuario> before = usuarioService.listAllUsuario();

        Usuario usuario = new Usuario();
        usuario.setNome("edinho");
        usuario.setEmail("edinho.pereira95@gmail.com");
        usuario.setSenha("123456");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("lio skrr");
        usuario2.setEmail("lio.crooncs@gmail.com");
        usuario2.setSenha("123456");

        usuarioService.saveUsuario(usuario);
        usuarioService.saveUsuario(usuario2);

        List<Usuario> after = usuarioService.listAllUsuario();
        Assertions.assertEquals(after.size(), before.size()+2);
    }

    @Test
    void editUsuario(){

        Usuario usuario = new Usuario();
        usuario.setNome("edinho");
        usuario.setEmail("edinho.pereira95@gmail.com");
        usuario.setSenha("123456");

        Usuario resultdb = usuarioService.saveUsuario(usuario);

        resultdb.setNome("Edson Pereira");
        Usuario updateUsuario = usuarioService.update(resultdb);
        Assertions.assertEquals(resultdb.getNome(),updateUsuario.getNome());
    }

    @Test
    void deleteUsuario(){
        List<Usuario> before = usuarioService.listAllUsuario();

        Usuario usuario = new Usuario();
        usuario.setNome("edinho");
        usuario.setEmail("edinho.pereira95@gmail.com");
        usuario.setSenha("123456");

        Usuario resultdb = usuarioService.saveUsuario(usuario);

        resultdb.setId(resultdb.getId());
        usuarioService.delete(resultdb.getId());

        List<Usuario> after = usuarioService.listAllUsuario();
         Assertions.assertEquals(after.size(), before.size());

    }
}
