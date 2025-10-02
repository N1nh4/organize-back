package com.example.organize.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
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
public class Atividade {
    private String nome;
    private String descricao;
    private String status;
    private String observacao;
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "atividade", cascade = jakarta.persistence.CascadeType.ALL, fetch = jakarta.persistence.FetchType.LAZY)
    private List<AtividadeColuna> colunas = new ArrayList<>();

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
