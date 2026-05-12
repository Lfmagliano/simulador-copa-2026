package com.simuladorcopa.backend.service;

import com.simuladorcopa.backend.model.Selecao;
import com.simuladorcopa.backend.repository.SelecaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SelecaoService {

    private final SelecaoRepository selecaoRepository;

    public List<Selecao> listarTodos() {
        return selecaoRepository.findAll();
    }
}