package com.example.organize.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.organize.model.Ponto;
import com.example.organize.model.Usuario;

public interface PontoRepository extends JpaRepository<Ponto, UUID> {

    List<Ponto> findByUsuarioAndPontoInicialBetween(
        Usuario usuario,
        LocalDateTime inicioDia,
        LocalDateTime fimDia
    );
    
} 
