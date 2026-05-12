package com.simuladorcopa.backend.controller;

import com.simuladorcopa.backend.model.Selecao;
import com.simuladorcopa.backend.service.SelecaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/selecoes")
public class SelecaoController {

    private final SelecaoService selecaoService;

    @GetMapping
    public List<Selecao> listarTodos() {
        return selecaoService.listarTodos();
    }
}