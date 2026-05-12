package com.simuladorcopa.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classificacaoGrupos")
public class ClassificacaoGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "selecao_id")
    private Selecao selecao;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @Column
    private Integer pontos;

    @Column
    private Integer vitorias;

    @Column 
    private Integer empates;

    @Column
    private Integer derrotas;

    @Column
    private Integer golsPro;

    @Column 
    private Integer golsContra;

    @Column
    private Integer saldoGols;

    @Column 
    private Integer cartoesAmarelos;

    @Column
    private Integer cartoesVermelhos;


}