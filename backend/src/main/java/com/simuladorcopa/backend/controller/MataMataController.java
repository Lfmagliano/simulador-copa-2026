package com.simuladorcopa.backend.controller;

import com.simuladorcopa.backend.model.Jogo;
import com.simuladorcopa.backend.repository.JogoRepository;
import com.simuladorcopa.backend.model.Fase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mata-mata")
public class MataMataController {

    private final JogoRepository jogoRepository;

    @GetMapping
    public List<Jogo> listarJogos() {
        return jogoRepository.findAll().stream()
            .filter(j -> j.getFase() != Fase.GRUPOS)
            .toList();
    }

    @GetMapping("/fase/{fase}")
    public List<Jogo> listarPorFase(@PathVariable String fase) {
        Fase faseEnum = Fase.valueOf(fase.toUpperCase());
        return jogoRepository.findAll().stream()
            .filter(j -> j.getFase() == faseEnum)
            .toList();
    }
}