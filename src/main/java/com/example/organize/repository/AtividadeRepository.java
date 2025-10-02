package com.example.organize.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.organize.model.Atividade;
import com.example.organize.model.Usuario;

public interface AtividadeRepository extends JpaRepository<Atividade, UUID> {

    List<Atividade> findByUsuario(Usuario usuario);
    
}
