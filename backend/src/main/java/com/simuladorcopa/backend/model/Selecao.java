package com.simuladorcopa.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Data               // Gera getters, setters, equals, hashCode e toString
@Entity             // Diz ao Spring que essa classe é uma tabela no banco
@Builder            // Permite criar objetos assim: Selecao.builder().nome("Brasil").build()
@NoArgsConstructor  // Gera construtor vazio (obrigatório para o JPA funcionar)
@AllArgsConstructor // Gera construtor com todos os campos (necessário para o @Builder)
@Table(name = "selecoes") // Nome da tabela no banco de dados
public class Selecao {

    @Id // Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Banco gera o ID automaticamente (1, 2, 3...)
    private Long id;

    @Column(nullable = false) // Campo obrigatório no banco
    private String nome;

    @Enumerated(EnumType.STRING) // Salva o enum como texto ("EUROPA") e não como número (0, 1, 2...)
    @Column(nullable = false)
    private Continente continente;

    @Column(nullable = false)
    private Integer forca;

    @ManyToOne                        // Muitas seleções pertencem a um grupo
    @JoinColumn(name = "grupo_id")    // Nome da coluna no banco
    private Grupo grupo;

}