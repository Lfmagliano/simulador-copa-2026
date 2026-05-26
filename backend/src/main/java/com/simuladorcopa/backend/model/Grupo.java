package com.simuladorcopa.backend.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @JsonIgnore                                        
    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
    private List<Selecao> selecoes;

}