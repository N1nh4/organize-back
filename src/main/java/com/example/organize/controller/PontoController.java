package com.example.organize.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.organize.model.Ponto;
import com.example.organize.service.PontoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pontos")
public class PontoController {
    
    private final PontoService pontoService;

    @PostMapping
    public ResponseEntity<Ponto> criarPonto(@RequestBody Ponto ponto) {
        Ponto novoPonto = pontoService.salvarPonto(ponto);
        return ResponseEntity.ok(novoPonto);
    }


}
