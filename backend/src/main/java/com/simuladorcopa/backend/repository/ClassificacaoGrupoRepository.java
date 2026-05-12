package com.simuladorcopa.backend.repository;

import com.simuladorcopa.backend.model.ClassificacaoGrupo;
import com.simuladorcopa.backend.model.Selecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassificacaoGrupoRepository extends JpaRepository<ClassificacaoGrupo, Long> {
    Optional<ClassificacaoGrupo> findBySelecao(Selecao selecao);
    List<ClassificacaoGrupo> findByGrupoId(Long grupoId);
    List<ClassificacaoGrupo> findAll();
}