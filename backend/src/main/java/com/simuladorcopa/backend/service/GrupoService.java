package com.simuladorcopa.backend.service;

import com.simuladorcopa.backend.model.Grupo;
import com.simuladorcopa.backend.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.simuladorcopa.backend.model.ClassificacaoGrupo;
import com.simuladorcopa.backend.repository.ClassificacaoGrupoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository grupoRepository;
    private final ClassificacaoGrupoRepository classificacaoGrupoRepository;

    public List<Grupo> listarTodos() {
        return grupoRepository.findAll();
    }

    public List<ClassificacaoGrupo> classificacaoDo(Long grupoId) {
    return classificacaoGrupoRepository.findByGrupoId(grupoId)
        .stream()
        .sorted((a, b) -> {
            // 1. Pontos
            if (!a.getPontos().equals(b.getPontos()))
                return b.getPontos() - a.getPontos();
            // 2. Saldo de gols
            if (!a.getSaldoGols().equals(b.getSaldoGols()))
                return b.getSaldoGols() - a.getSaldoGols();
            // 3. Gols pró
            return b.getGolsPro() - a.getGolsPro();
        })
        .toList();
    }

}