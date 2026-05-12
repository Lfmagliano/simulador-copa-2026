package com.simuladorcopa.backend.repository;

// Importa a entidade que esse repository vai gerenciar
import com.simuladorcopa.backend.model.Grupo;

// Importa a interface do Spring que já tem os métodos prontos
import org.springframework.data.jpa.repository.JpaRepository;

// Importa a anotação que diz ao Spring que isso é um repository
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    // Não precisa escrever nada aqui!
    // O Spring já gera automaticamente:
    // - findAll()         → busca todos os grupos
    // - findById(id)      → busca por id
    // - save(grupo)       → salva ou atualiza
    // - deleteById(id)    → deleta por id
    // - count()           → conta quantos grupos existem
}