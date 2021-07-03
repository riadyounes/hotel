package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Usuario;
import com.agendamento.hotel.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/usuarios")

public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> store(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.store(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> index() {
        return ResponseEntity.ok(usuarioService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> show(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioService.show(id);

        if (optionalUsuario.isPresent()) {
            usuario.setId(id);
            return ResponseEntity.ok(usuarioService.update(usuario));
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id) {
        usuarioService.destroy(id);
        return ResponseEntity.ok(null);
    }
}
