package com.example.organize.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.organize.model.Ponto;
import com.example.organize.model.Usuario;
import com.example.organize.repository.PontoRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class PontoService {
    
    private final PontoRepository pontoRepository;

    public Ponto salvarPonto(Ponto ponto) {
        return pontoRepository.save(ponto);
    }

    public Ponto atualizarPonto(Ponto ponto) {
        return pontoRepository.save(ponto);
    }

    public List<Ponto> listarPontos() {
        return pontoRepository.findAll();
    }

    // Converte a meta de string para Duration
    private Duration parseMeta(String metaStr) {
    if (metaStr == null || metaStr.isBlank()) return null;

    try {
        // formato "HH:mm"
        if (metaStr.contains(":")) {
            LocalTime lt = LocalTime.parse(metaStr); // ex: "08:30"
            return Duration.between(LocalTime.MIDNIGHT, lt);
        }

        // ISO-8601 "PT8H30M" ou "PT8H"
        if (metaStr.startsWith("PT") || metaStr.startsWith("P")) {
            return Duration.parse(metaStr);
        }

        // número em minutos
        long minutes = Long.parseLong(metaStr);
        return Duration.ofMinutes(minutes);

    } catch (DateTimeParseException | NumberFormatException ex) {
        throw new IllegalArgumentException("Formato de meta inválido. Use HH:mm, ISO-8601 (PT...) ou minutos.", ex);
    }
}

    public Ponto registrarPonto(Usuario usuario, String metaStr) {
        Duration meta = parseMeta(metaStr); // converte ou null

        LocalDateTime inicioDia = LocalDate.now().atStartOfDay();
        LocalDateTime fimDia = inicioDia.plusDays(1).minusSeconds(1);

        List<Ponto> pontosDoDia = pontoRepository.findByUsuarioAndPontoInicialBetween(usuario, inicioDia, fimDia);

        if (pontosDoDia == null || pontosDoDia.isEmpty()) {
            Ponto novoPonto = new Ponto();
            novoPonto.setUsuario(usuario);
            novoPonto.setMeta(meta);
            novoPonto.setPontoInicial(LocalDateTime.now());
            return pontoRepository.save(novoPonto);
        } else {
            // assume que pontosDoDia está ordenado pelo pontoInicial; se não, ordene
            Ponto ultimoPonto = pontosDoDia.get(pontosDoDia.size() - 1);

            if (ultimoPonto.getPontoFinal() == null) {
                ultimoPonto.setPontoFinal(LocalDateTime.now());
                return pontoRepository.save(ultimoPonto);
            } else {
                // cria novo ponto (retorno de pausa)
                Ponto novoPonto = new Ponto();
                novoPonto.setUsuario(usuario);
                novoPonto.setMeta(ultimoPonto.getMeta() != null ? ultimoPonto.getMeta() : meta); // mantém meta anterior se existir
                novoPonto.setPontoInicial(LocalDateTime.now());
                return pontoRepository.save(novoPonto);
            }
        }
    }


    

    // Função para buscar pontos de um usuário em um dia específico
    public List<Ponto> buscarPontoDiario(Usuario usuario, LocalDateTime inicioDia, LocalDateTime fimDia) {
        List<Ponto> pontos = pontoRepository.findByUsuarioAndPontoInicialBetween(usuario, inicioDia, fimDia);

        return pontos;
    }


    // Função para calcular o total de horas trabalhadas em um dia com base nos pontos registrados
    public Duration calcularHoraTotal(List<Ponto> pontos) {
        pontos.sort(Comparator.comparing(Ponto::getPontoInicial));

        Duration horaTotal = Duration.ZERO;

        for (int i = 0; i < pontos.size(); i++) {
            Ponto ponto = pontos.get(i);

            if (ponto.getPontoFinal() != null && ponto.getPontoInicial() != null) {
                Duration duracao = Duration.between(ponto.getPontoInicial(), ponto.getPontoFinal());
                horaTotal = horaTotal.plus(duracao);
            }
        }

        return horaTotal;
    }


    // Função para calcular o número de pausas em um dia com base nos pontos registrados
    public int calcularPausas(List<Ponto> pontos) {
            // Ordenar os pontos pelo horário de início
            pontos.sort(Comparator.comparing(Ponto::getPontoInicial));

            int pausas =  0;

            for (int i = 1; i < pontos.size(); i++) {
                Ponto anterior = pontos.get(i - 1);
                Ponto atual = pontos.get(i);

                if (anterior.getPontoFinal() != null && atual.getPontoInicial() != null) {
                    pausas++;
                }
            }

            return pausas;
        }

}
