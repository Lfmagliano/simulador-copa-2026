package com.simuladorcopa.backend;

import com.simuladorcopa.backend.model.*;
import com.simuladorcopa.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final GrupoRepository grupoRepository;
    private final SelecaoRepository selecaoRepository;
    private final JogoRepository jogoRepository;
    private final ClassificacaoGrupoRepository classificacaoGrupoRepository;

    @Override
    public void run(String... args) {
        if (grupoRepository.count() == 0) {

            List<Grupo> grupos = grupoRepository.saveAll(List.of(
                Grupo.builder().nome("A").build(),
                Grupo.builder().nome("B").build(),
                Grupo.builder().nome("C").build(),
                Grupo.builder().nome("D").build(),
                Grupo.builder().nome("E").build(),
                Grupo.builder().nome("F").build(),
                Grupo.builder().nome("G").build(),
                Grupo.builder().nome("H").build(),
                Grupo.builder().nome("I").build(),
                Grupo.builder().nome("J").build(),
                Grupo.builder().nome("K").build(),
                Grupo.builder().nome("L").build()
            ));

            Grupo A = grupos.get(0),  B = grupos.get(1),
                  C = grupos.get(2),  D = grupos.get(3),
                  E = grupos.get(4),  F = grupos.get(5),
                  G = grupos.get(6),  H = grupos.get(7),
                  I = grupos.get(8),  J = grupos.get(9),
                  K = grupos.get(10), L = grupos.get(11);

            List<Selecao> selecoes = selecaoRepository.saveAll(List.of(
                // Grupo A
                Selecao.builder().nome("México").continente(Continente.AMERICA_DO_NORTE).grupo(A).forca(91).build(),
                Selecao.builder().nome("África do Sul").continente(Continente.AFRICA).grupo(A).forca(64).build(),
                Selecao.builder().nome("Coreia do Sul").continente(Continente.ASIA).grupo(A).forca(85).build(),
                Selecao.builder().nome("República Tcheca").continente(Continente.EUROPA).grupo(A).forca(75).build(),
                // Grupo B
                Selecao.builder().nome("Canadá").continente(Continente.AMERICA_DO_NORTE).grupo(B).forca(82).build(),
                Selecao.builder().nome("Bósnia").continente(Continente.EUROPA).grupo(B).forca(61).build(),
                Selecao.builder().nome("Qatar").continente(Continente.ASIA).grupo(B).forca(67).build(),
                Selecao.builder().nome("Suíça").continente(Continente.EUROPA).grupo(B).forca(88).build(),
                // Grupo C
                Selecao.builder().nome("Brasil").continente(Continente.AMERICA_DO_SUL).grupo(C).forca(96).build(),
                Selecao.builder().nome("Marrocos").continente(Continente.AFRICA).grupo(C).forca(95).build(),
                Selecao.builder().nome("Haiti").continente(Continente.AMERICA_DO_NORTE).grupo(C).forca(50).build(),
                Selecao.builder().nome("Escócia").continente(Continente.EUROPA).grupo(C).forca(74).build(),
                // Grupo D
                Selecao.builder().nome("Estados Unidos").continente(Continente.AMERICA_DO_NORTE).grupo(D).forca(90).build(),
                Selecao.builder().nome("Paraguai").continente(Continente.AMERICA_DO_SUL).grupo(D).forca(76).build(),
                Selecao.builder().nome("Austrália").continente(Continente.OCEANIA).grupo(D).forca(83).build(),
                Selecao.builder().nome("Turquia").continente(Continente.EUROPA).grupo(D).forca(86).build(),
                // Grupo E
                Selecao.builder().nome("Alemanha").continente(Continente.EUROPA).grupo(E).forca(94).build(),
                Selecao.builder().nome("Curaçao").continente(Continente.AMERICA_DO_NORTE).grupo(E).forca(50).build(),
                Selecao.builder().nome("Costa do Marfim").continente(Continente.AFRICA).grupo(E).forca(79).build(),
                Selecao.builder().nome("Equador").continente(Continente.AMERICA_DO_SUL).grupo(E).forca(86).build(),
                // Grupo F
                Selecao.builder().nome("Holanda").continente(Continente.EUROPA).grupo(F).forca(95).build(),
                Selecao.builder().nome("Japão").continente(Continente.ASIA).grupo(F).forca(89).build(),
                Selecao.builder().nome("Suécia").continente(Continente.EUROPA).grupo(F).forca(77).build(),
                Selecao.builder().nome("Tunísia").continente(Continente.AFRICA).grupo(F).forca(73).build(),
                // Grupo G
                Selecao.builder().nome("Bélgica").continente(Continente.EUROPA).grupo(G).forca(94).build(),
                Selecao.builder().nome("Egito").continente(Continente.AFRICA).grupo(G).forca(82).build(),
                Selecao.builder().nome("Irã").continente(Continente.ASIA).grupo(G).forca(87).build(),
                Selecao.builder().nome("Nova Zelândia").continente(Continente.OCEANIA).grupo(G).forca(49).build(),
                // Grupo H
                Selecao.builder().nome("Espanha").continente(Continente.EUROPA).grupo(H).forca(98).build(),
                Selecao.builder().nome("Cabo Verde").continente(Continente.AFRICA).grupo(H).forca(58).build(),
                Selecao.builder().nome("Arábia Saudita").continente(Continente.ASIA).grupo(H).forca(63).build(),
                Selecao.builder().nome("Uruguai").continente(Continente.AMERICA_DO_SUL).grupo(H).forca(89).build(),
                // Grupo I
                Selecao.builder().nome("França").continente(Continente.EUROPA).grupo(I).forca(99).build(),
                Selecao.builder().nome("Senegal").continente(Continente.AFRICA).grupo(I).forca(91).build(),
                Selecao.builder().nome("Iraque").continente(Continente.ASIA).grupo(I).forca(65).build(),
                Selecao.builder().nome("Noruega").continente(Continente.EUROPA).grupo(I).forca(81).build(),
                // Grupo J
                Selecao.builder().nome("Argentina").continente(Continente.AMERICA_DO_SUL).grupo(J).forca(98).build(),
                Selecao.builder().nome("Argélia").continente(Continente.AFRICA).grupo(J).forca(83).build(),
                Selecao.builder().nome("Áustria").continente(Continente.EUROPA).grupo(J).forca(85).build(),
                Selecao.builder().nome("Jordânia").continente(Continente.ASIA).grupo(J).forca(62).build(),
                // Grupo K
                Selecao.builder().nome("Portugal").continente(Continente.EUROPA).grupo(K).forca(97).build(),
                Selecao.builder().nome("Congo RD").continente(Continente.AFRICA).grupo(K).forca(72).build(),
                Selecao.builder().nome("Uzbequistão").continente(Continente.ASIA).grupo(K).forca(70).build(),
                Selecao.builder().nome("Colômbia").continente(Continente.AMERICA_DO_SUL).grupo(K).forca(92).build(),
                // Grupo L
                Selecao.builder().nome("Inglaterra").continente(Continente.EUROPA).grupo(L).forca(97).build(),
                Selecao.builder().nome("Croácia").continente(Continente.EUROPA).grupo(L).forca(93).build(),
                Selecao.builder().nome("Gana").continente(Continente.AFRICA).grupo(L).forca(55).build(),
                Selecao.builder().nome("Panamá").continente(Continente.AMERICA_DO_NORTE).grupo(L).forca(80).build()
            ));

            List<ClassificacaoGrupo> classificacoes = new ArrayList<>();
            for (Selecao selecao : selecoes) {
                classificacoes.add(ClassificacaoGrupo.builder()
                    .selecao(selecao)
                    .grupo(selecao.getGrupo())
                    .pontos(0).vitorias(0).empates(0).derrotas(0)
                    .golsPro(0).golsContra(0).saldoGols(0)
                    .cartoesAmarelos(0).cartoesVermelhos(0)
                    .build());
            }
            classificacaoGrupoRepository.saveAll(classificacoes);

            List<Jogo> jogos = new ArrayList<>();

            // ===== GRUPO A =====
            // sg: 0=México, 1=África do Sul, 2=Coreia do Sul, 3=República Tcheca
            List<Selecao> sgA = selecoes.stream().filter(s -> s.getGrupo().getId().equals(A.getId())).toList();
            // Rodada 1: México x África do Sul | Coreia do Sul x República Tcheca
            jogos.add(Jogo.builder().selecaoCasa(sgA.get(0)).selecaoVisitante(sgA.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgA.get(2)).selecaoVisitante(sgA.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: República Tcheca x África do Sul | México x Coreia do Sul
            jogos.add(Jogo.builder().selecaoCasa(sgA.get(3)).selecaoVisitante(sgA.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgA.get(0)).selecaoVisitante(sgA.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: República Tcheca x México | África do Sul x Coreia do Sul
            jogos.add(Jogo.builder().selecaoCasa(sgA.get(3)).selecaoVisitante(sgA.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgA.get(1)).selecaoVisitante(sgA.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO B =====
            // sg: 0=Canadá, 1=Bósnia, 2=Qatar, 3=Suíça
            List<Selecao> sgB = selecoes.stream().filter(s -> s.getGrupo().getId().equals(B.getId())).toList();
            // Rodada 1: Canadá x Bósnia | Qatar x Suíça
            jogos.add(Jogo.builder().selecaoCasa(sgB.get(0)).selecaoVisitante(sgB.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgB.get(2)).selecaoVisitante(sgB.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: Suíça x Bósnia | Canadá x Qatar
            jogos.add(Jogo.builder().selecaoCasa(sgB.get(3)).selecaoVisitante(sgB.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgB.get(0)).selecaoVisitante(sgB.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Suíça x Canadá | Bósnia x Qatar
            jogos.add(Jogo.builder().selecaoCasa(sgB.get(3)).selecaoVisitante(sgB.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgB.get(1)).selecaoVisitante(sgB.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO C =====
            // sg: 0=Brasil, 1=Marrocos, 2=Haiti, 3=Escócia
            List<Selecao> sgC = selecoes.stream().filter(s -> s.getGrupo().getId().equals(C.getId())).toList();
            // Rodada 1: Brasil x Marrocos | Haiti x Escócia
            jogos.add(Jogo.builder().selecaoCasa(sgC.get(0)).selecaoVisitante(sgC.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgC.get(2)).selecaoVisitante(sgC.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: Brasil x Haiti | Escócia x Marrocos
            jogos.add(Jogo.builder().selecaoCasa(sgC.get(0)).selecaoVisitante(sgC.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgC.get(3)).selecaoVisitante(sgC.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Escócia x Brasil | Marrocos x Haiti
            jogos.add(Jogo.builder().selecaoCasa(sgC.get(3)).selecaoVisitante(sgC.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgC.get(1)).selecaoVisitante(sgC.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO D =====
            // sg: 0=Estados Unidos, 1=Paraguai, 2=Austrália, 3=Turquia
            List<Selecao> sgD = selecoes.stream().filter(s -> s.getGrupo().getId().equals(D.getId())).toList();
            // Rodada 1: EUA x Paraguai | Austrália x Turquia
            jogos.add(Jogo.builder().selecaoCasa(sgD.get(0)).selecaoVisitante(sgD.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgD.get(2)).selecaoVisitante(sgD.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: EUA x Austrália | Turquia x Paraguai
            jogos.add(Jogo.builder().selecaoCasa(sgD.get(0)).selecaoVisitante(sgD.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgD.get(3)).selecaoVisitante(sgD.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Turquia x EUA | Paraguai x Austrália
            jogos.add(Jogo.builder().selecaoCasa(sgD.get(3)).selecaoVisitante(sgD.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgD.get(1)).selecaoVisitante(sgD.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO E =====
            // sg: 0=Alemanha, 1=Curaçao, 2=Costa do Marfim, 3=Equador
            List<Selecao> sgE = selecoes.stream().filter(s -> s.getGrupo().getId().equals(E.getId())).toList();
            // Rodada 1: Alemanha x Curaçao | Costa do Marfim x Equador
            jogos.add(Jogo.builder().selecaoCasa(sgE.get(0)).selecaoVisitante(sgE.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgE.get(2)).selecaoVisitante(sgE.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: Alemanha x Costa do Marfim | Equador x Curaçao
            jogos.add(Jogo.builder().selecaoCasa(sgE.get(0)).selecaoVisitante(sgE.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgE.get(3)).selecaoVisitante(sgE.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Equador x Alemanha | Curaçao x Costa do Marfim
            jogos.add(Jogo.builder().selecaoCasa(sgE.get(3)).selecaoVisitante(sgE.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgE.get(1)).selecaoVisitante(sgE.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO F =====
            // sg: 0=Holanda, 1=Japão, 2=Suécia, 3=Tunísia
            List<Selecao> sgF = selecoes.stream().filter(s -> s.getGrupo().getId().equals(F.getId())).toList();
            // Rodada 1: Holanda x Japão | Suécia x Tunísia
            jogos.add(Jogo.builder().selecaoCasa(sgF.get(0)).selecaoVisitante(sgF.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgF.get(2)).selecaoVisitante(sgF.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: Holanda x Suécia | Tunísia x Japão
            jogos.add(Jogo.builder().selecaoCasa(sgF.get(0)).selecaoVisitante(sgF.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgF.get(3)).selecaoVisitante(sgF.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Tunísia x Holanda | Japão x Suécia
            jogos.add(Jogo.builder().selecaoCasa(sgF.get(3)).selecaoVisitante(sgF.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgF.get(1)).selecaoVisitante(sgF.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO G =====
            // sg: 0=Bélgica, 1=Egito, 2=Irã, 3=Nova Zelândia
            List<Selecao> sgG = selecoes.stream().filter(s -> s.getGrupo().getId().equals(G.getId())).toList();
            // Rodada 1: Bélgica x Egito | Irã x Nova Zelândia
            jogos.add(Jogo.builder().selecaoCasa(sgG.get(0)).selecaoVisitante(sgG.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgG.get(2)).selecaoVisitante(sgG.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: Bélgica x Irã | Nova Zelândia x Egito
            jogos.add(Jogo.builder().selecaoCasa(sgG.get(0)).selecaoVisitante(sgG.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgG.get(3)).selecaoVisitante(sgG.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Nova Zelândia x Bélgica | Egito x Irã
            jogos.add(Jogo.builder().selecaoCasa(sgG.get(3)).selecaoVisitante(sgG.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgG.get(1)).selecaoVisitante(sgG.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO H =====
            // sg: 0=Espanha, 1=Cabo Verde, 2=Arábia Saudita, 3=Uruguai
            List<Selecao> sgH = selecoes.stream().filter(s -> s.getGrupo().getId().equals(H.getId())).toList();
            // Rodada 1: Espanha x Cabo Verde | Arábia Saudita x Uruguai
            jogos.add(Jogo.builder().selecaoCasa(sgH.get(0)).selecaoVisitante(sgH.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgH.get(2)).selecaoVisitante(sgH.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: Espanha x Arábia Saudita | Uruguai x Cabo Verde
            jogos.add(Jogo.builder().selecaoCasa(sgH.get(0)).selecaoVisitante(sgH.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgH.get(3)).selecaoVisitante(sgH.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Uruguai x Espanha | Cabo Verde x Arábia Saudita
            jogos.add(Jogo.builder().selecaoCasa(sgH.get(3)).selecaoVisitante(sgH.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgH.get(1)).selecaoVisitante(sgH.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO I =====
            // sg: 0=França, 1=Senegal, 2=Iraque, 3=Noruega
            List<Selecao> sgI = selecoes.stream().filter(s -> s.getGrupo().getId().equals(I.getId())).toList();
            // Rodada 1: França x Senegal | Iraque x Noruega
            jogos.add(Jogo.builder().selecaoCasa(sgI.get(0)).selecaoVisitante(sgI.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgI.get(2)).selecaoVisitante(sgI.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: França x Iraque | Noruega x Senegal
            jogos.add(Jogo.builder().selecaoCasa(sgI.get(0)).selecaoVisitante(sgI.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgI.get(3)).selecaoVisitante(sgI.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Noruega x França | Senegal x Iraque
            jogos.add(Jogo.builder().selecaoCasa(sgI.get(3)).selecaoVisitante(sgI.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgI.get(1)).selecaoVisitante(sgI.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO J =====
            // sg: 0=Argentina, 1=Argélia, 2=Áustria, 3=Jordânia
            List<Selecao> sgJ = selecoes.stream().filter(s -> s.getGrupo().getId().equals(J.getId())).toList();
            // Rodada 1: Argentina x Argélia | Áustria x Jordânia
            jogos.add(Jogo.builder().selecaoCasa(sgJ.get(0)).selecaoVisitante(sgJ.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgJ.get(2)).selecaoVisitante(sgJ.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: Argentina x Áustria | Jordânia x Argélia
            jogos.add(Jogo.builder().selecaoCasa(sgJ.get(0)).selecaoVisitante(sgJ.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgJ.get(3)).selecaoVisitante(sgJ.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Jordânia x Argentina | Argélia x Áustria
            jogos.add(Jogo.builder().selecaoCasa(sgJ.get(3)).selecaoVisitante(sgJ.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgJ.get(1)).selecaoVisitante(sgJ.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO K =====
            // sg: 0=Portugal, 1=Congo RD, 2=Uzbequistão, 3=Colômbia
            List<Selecao> sgK = selecoes.stream().filter(s -> s.getGrupo().getId().equals(K.getId())).toList();
            // Rodada 1: Portugal x Congo RD | Uzbequistão x Colômbia
            jogos.add(Jogo.builder().selecaoCasa(sgK.get(0)).selecaoVisitante(sgK.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgK.get(2)).selecaoVisitante(sgK.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: Portugal x Uzbequistão | Colômbia x Congo RD
            jogos.add(Jogo.builder().selecaoCasa(sgK.get(0)).selecaoVisitante(sgK.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgK.get(3)).selecaoVisitante(sgK.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Colômbia x Portugal | Congo RD x Uzbequistão
            jogos.add(Jogo.builder().selecaoCasa(sgK.get(3)).selecaoVisitante(sgK.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgK.get(1)).selecaoVisitante(sgK.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            // ===== GRUPO L =====
            // sg: 0=Inglaterra, 1=Croácia, 2=Gana, 3=Panamá
            List<Selecao> sgL = selecoes.stream().filter(s -> s.getGrupo().getId().equals(L.getId())).toList();
            // Rodada 1: Inglaterra x Croácia | Gana x Panamá
            jogos.add(Jogo.builder().selecaoCasa(sgL.get(0)).selecaoVisitante(sgL.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgL.get(2)).selecaoVisitante(sgL.get(3)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(1).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 2: Inglaterra x Gana | Panamá x Croácia
            jogos.add(Jogo.builder().selecaoCasa(sgL.get(0)).selecaoVisitante(sgL.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgL.get(3)).selecaoVisitante(sgL.get(1)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(2).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            // Rodada 3: Panamá x Inglaterra | Croácia x Gana
            jogos.add(Jogo.builder().selecaoCasa(sgL.get(3)).selecaoVisitante(sgL.get(0)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());
            jogos.add(Jogo.builder().selecaoCasa(sgL.get(1)).selecaoVisitante(sgL.get(2)).golsCasa(0).golsVisitante(0).fase(Fase.GRUPOS).rodada(3).encerrado(false).temProrrogacao(false).temPenaltis(false).build());

            jogoRepository.saveAll(jogos);

            System.out.println(grupos.size() + " grupos criados");
            System.out.println(selecoes.size() + " seleções criadas");
            System.out.println(classificacoes.size() + " classificações criadas");
            System.out.println(jogos.size() + " jogos criados");
        }
    }
}