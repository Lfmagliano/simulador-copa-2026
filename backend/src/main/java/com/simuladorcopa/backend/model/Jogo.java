package com.simuladorcopa.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "selecao_casa_id")
    private Selecao selecaoCasa;

    @ManyToOne
    @JoinColumn(name = "selecao_visitante_id")
    private Selecao selecaoVisitante;

    @Column
    private Integer golsCasa;

    @Column
    private Integer golsVisitante;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Fase fase;

    @Column(nullable = false)
    private Integer rodada;

    @Column(nullable = false)
    private Boolean encerrado;

    @Column(nullable = false)
    private Boolean temProrrogacao;

    @Column(nullable = false)
    private Boolean temPenaltis;

}