package com.simuladorcopa.backend.controller;

import com.simuladorcopa.backend.model.Jogo;
import com.simuladorcopa.backend.service.JogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jogos")
public class JogoController {

    private final JogoService jogoService;

    @GetMapping
    public List<Jogo> listarTodos() {
        return jogoService.listarTodos();
    }

    @PostMapping("/simular/{id}")
    public Jogo simular(@PathVariable Long id) {
        return jogoService.simular(id);
    }

    @PostMapping("/resultado/{id}")
    public Jogo registrarResultado(
        @PathVariable Long id,
        @RequestBody Map<String, Integer> body
    ) {
        return jogoService.registrarResultado(
            id,
            body.get("golsCasa"),
            body.get("golsVisitante")
        );
    }
}