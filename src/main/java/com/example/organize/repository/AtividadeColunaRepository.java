package com.example.organize.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.organize.model.AtividadeColuna;

public interface AtividadeColunaRepository extends JpaRepository<AtividadeColuna, UUID> {
        
}
