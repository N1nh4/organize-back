package com.example.organize.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.organize.model.Atividade;
import com.example.organize.model.Usuario;
import com.example.organize.repository.AtividadeRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AtividadeService {
    
    private final AtividadeRepository atividadeRepository;

    public Atividade salvarAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public void deletarAtividade(Atividade atividade) {
        atividadeRepository.delete(atividade);
    }

    public Atividade atualizarAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }


    public List<Atividade> buscarAtividadePorUsuario(Usuario usuario) {
        return atividadeRepository.findByUsuario(usuario);
    }

    // útil para quando já temos o ID da atividade e o usuario quer editar essa atividade
    public Optional<Atividade> buscarPorId(UUID id) {
        return atividadeRepository.findById(id);

    }

    





    
}
