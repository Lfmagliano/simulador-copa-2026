package com.simuladorcopa.backend.controller;

import com.simuladorcopa.backend.model.Grupo;
import com.simuladorcopa.backend.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.simuladorcopa.backend.model.ClassificacaoGrupo;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grupos")
public class GrupoController {

    private final GrupoService grupoService;

    @GetMapping
    public List<Grupo> listarTodos() {
        return grupoService.listarTodos();
    }

    @GetMapping("/{id}/classificacao")
    public List<ClassificacaoGrupo> classificacao(@PathVariable Long id) {
    return grupoService.classificacaoDo(id);
    }

}