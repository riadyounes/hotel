package com.agendamento.hotel.controller;

import com.agendamento.hotel.model.Usuario;
import com.agendamento.hotel.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) throws  Exception {
        try {
            Usuario usuarioSaved = usuarioService.saveUsuario(usuario);
            return  new ResponseEntity<>(usuarioSaved, null, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        List<Usuario> usuarioList = usuarioService.listAllUsuario();
        if (!usuarioList.isEmpty()){
            return new ResponseEntity<>(usuarioList, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioList, null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> findById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.update(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@RequestBody Usuario usuario){
        return ResponseEntity.ok(null);
    }
}
