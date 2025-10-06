package com.example.organize.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.organize.model.Usuario;
import com.example.organize.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Salvar um novo usuário
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Buscar usuário por email e senha para o login
    public Usuario login(String email, String senha) {
        return usuarioRepository.findByEmail(email).filter(u -> u.getSenha().equals(senha)).orElse(null);
    }

    // Buscar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por ID
    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    //Alterar usuário
    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Deletar usuário por ID
    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

}
