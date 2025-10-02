package com.example.organize.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.organize.model.AtividadeConteudo;

public interface AtividadeConteudoRepository extends JpaRepository<AtividadeConteudo, UUID> {
        
}
