package com.example.organize.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class AtividadeColuna {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private LocalDateTime dataCriacao;  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_atividade")
    private Atividade atividade;

    @OneToMany(mappedBy = "atividadeColuna", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AtividadeConteudo> conteudos = new ArrayList<>();
}
