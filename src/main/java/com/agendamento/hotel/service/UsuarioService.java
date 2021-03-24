package com.agendamento.hotel.service;

import com.agendamento.hotel.model.Usuario;
import com.agendamento.hotel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findOne(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> listAllUsuario() {
        return usuarioRepository.findAll();
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        Usuario usuario = usuarioRepository.getOne(id);
        usuarioRepository.delete(usuario);
    }
}
