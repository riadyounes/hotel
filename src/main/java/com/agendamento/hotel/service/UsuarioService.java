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

    public Usuario store(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> index() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> show(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void destroy(Long id) {
        Optional<Usuario> optionalUsuario = this.show(id);

        if (optionalUsuario.isPresent()) {
            usuarioRepository.deleteById(id);
        }
    }
}
