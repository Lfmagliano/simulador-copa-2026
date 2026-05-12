package com.simuladorcopa.backend.controller;

import com.simuladorcopa.backend.DataLoader;
import com.simuladorcopa.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final JogoRepository jogoRepository;
    private final ClassificacaoGrupoRepository classificacaoGrupoRepository;
    private final SelecaoRepository selecaoRepository;
    private final GrupoRepository grupoRepository;
    private final DataLoader dataLoader;

    @PostMapping("/reset")
    public String reset() {
        jogoRepository.deleteAll();
        classificacaoGrupoRepository.deleteAll();
        selecaoRepository.deleteAll();
        grupoRepository.deleteAll();

        try {
            dataLoader.run();
        } catch (Exception e) {
            return "Erro ao recriar dados: " + e.getMessage();
        }

        return "✅ Simulação resetada e recriada com sucesso!";
    }
}