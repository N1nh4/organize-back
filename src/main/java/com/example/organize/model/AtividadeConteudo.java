package com.example.organize.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class AtividadeConteudo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String conteudo;
    private LocalDateTime dataCriacao;  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_atividade_coluna")
    private AtividadeColuna atividadeColuna;


}
