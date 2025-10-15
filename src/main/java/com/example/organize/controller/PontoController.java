package com.example.organize.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.organize.model.Ponto;
import com.example.organize.model.Usuario;
import com.example.organize.service.PontoService;
import com.example.organize.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pontos")
public class PontoController {
    
    private final PontoService pontoService;
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Ponto> criarPonto(@RequestBody Ponto ponto) {
        Ponto novoPonto = pontoService.salvarPonto(ponto);
        return ResponseEntity.ok(novoPonto);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Ponto> registrarPonto(@RequestBody Map<String, String> body) {
        String meta = body.get("meta");
        String idUsuario = body.get("id_usuario");

        Usuario usuario = new Usuario();
        usuario.setId(UUID.fromString(idUsuario));

        Ponto ponto = pontoService.registrarPonto(usuario, meta);

        return ResponseEntity.ok(ponto);
    }

    @GetMapping("/visualizarPontoDiario")
    public List<Ponto> buscarPontoDiario(
            @RequestParam UUID usuarioID,
            @RequestParam String data
    ) {
        Usuario usuario = usuarioService.buscarPorId(usuarioID)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        LocalDate localDate = LocalDate.parse(data);
        LocalDateTime inicioDia = localDate.atStartOfDay();
        LocalDateTime fimDia = localDate.atTime(23, 59, 59);

        return pontoService.buscarPontoDiario(usuario, inicioDia, fimDia);
    }


}
