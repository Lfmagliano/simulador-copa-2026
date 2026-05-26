package com.simuladorcopa.backend.service;

import com.simuladorcopa.backend.model.*;
import com.simuladorcopa.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MataMataService {

    private final JogoRepository jogoRepository;
    private final GrupoRepository grupoRepository;
    private final ClassificacaoGrupoRepository classificacaoGrupoRepository;

    public boolean faseGruposEncerrada() {
        List<Jogo> jogosGrupos = jogoRepository.findAll().stream()
            .filter(j -> j.getFase() == Fase.GRUPOS)
            .toList();
        return !jogosGrupos.isEmpty() && jogosGrupos.stream().allMatch(Jogo::getEncerrado);
    }

    public boolean mataMataGerado() {
        return jogoRepository.findAll().stream()
            .anyMatch(j -> j.getFase() == Fase.DEZESSEIS_AVOS);
    }

    private List<ClassificacaoGrupo> classificarGrupo(Long grupoId) {
        return classificacaoGrupoRepository.findByGrupoId(grupoId)
            .stream()
            .sorted(this::compararClassificacao)
            .collect(Collectors.toList());
    }

    private int compararClassificacao(ClassificacaoGrupo a, ClassificacaoGrupo b) {
        if (!a.getPontos().equals(b.getPontos()))
            return b.getPontos() - a.getPontos();
        if (!a.getSaldoGols().equals(b.getSaldoGols()))
            return b.getSaldoGols() - a.getSaldoGols();
        if (!a.getGolsPro().equals(b.getGolsPro()))
            return b.getGolsPro() - a.getGolsPro();
        return b.getSelecao().getForca() - a.getSelecao().getForca();
    }

    public void gerarMataMata() {
        if (!faseGruposEncerrada() || mataMataGerado()) return;

        List<Grupo> grupos = grupoRepository.findAll()
            .stream()
            .sorted(Comparator.comparing(Grupo::getNome))
            .collect(Collectors.toList());

        List<Selecao> primeiros = new ArrayList<>();
        List<Selecao> segundos = new ArrayList<>();
        List<ClassificacaoGrupo> terceiros = new ArrayList<>();

        for (Grupo grupo : grupos) {
            List<ClassificacaoGrupo> classif = classificarGrupo(grupo.getId());
            if (classif.size() >= 1) primeiros.add(classif.get(0).getSelecao());
            if (classif.size() >= 2) segundos.add(classif.get(1).getSelecao());
            if (classif.size() >= 3) terceiros.add(classif.get(2));
        }

        List<Selecao> melhoresTerceiros = terceiros.stream()
            .sorted(this::compararClassificacao)
            .limit(8)
            .map(ClassificacaoGrupo::getSelecao)
            .collect(Collectors.toList());

        List<Jogo> jogos = new ArrayList<>();

        // 8 confrontos: 1º vs 2º de grupos diferentes
        jogos.add(criarJogo(primeiros.get(0),  segundos.get(11), Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(primeiros.get(1),  segundos.get(10), Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(primeiros.get(2),  segundos.get(9),  Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(primeiros.get(3),  segundos.get(8),  Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(primeiros.get(4),  segundos.get(7),  Fase.DEZESSEIS_AVOS));
        jogos.add(criarJogo(primeiros.get(5),  segundos.get(6),  Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(primeiros.get(6),  segundos.get(5),  Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(primeiros.get(7),  segundos.get(4),  Fase.DEZESSEIS_AVOS)); 

        // 8 confrontos: 1º dos grupos I-L + 2º dos grupos A-D vs melhores terceiros
        jogos.add(criarJogo(primeiros.get(8),  melhoresTerceiros.get(0), Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(primeiros.get(9),  melhoresTerceiros.get(1), Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(primeiros.get(10), melhoresTerceiros.get(2), Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(primeiros.get(11), melhoresTerceiros.get(3), Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(segundos.get(0),   melhoresTerceiros.get(4), Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(segundos.get(1),   melhoresTerceiros.get(5), Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(segundos.get(2),   melhoresTerceiros.get(6), Fase.DEZESSEIS_AVOS)); 
        jogos.add(criarJogo(segundos.get(3),   melhoresTerceiros.get(7), Fase.DEZESSEIS_AVOS)); 

        jogoRepository.saveAll(jogos);
        System.out.println("Rodada de 32 gerada com " + jogos.size() + " jogos!");
    }

    public void avancarFase(Fase faseAtual) {
        List<Jogo> jogosFase = jogoRepository.findAll().stream()
            .filter(j -> j.getFase() == faseAtual)
            .toList();

        if (jogosFase.isEmpty() || !jogosFase.stream().allMatch(Jogo::getEncerrado)) return;

        Fase proximaFase = proximaFase(faseAtual);
        if (proximaFase == null) return;

        boolean proximaJaGerada = jogoRepository.findAll().stream()
            .anyMatch(j -> j.getFase() == proximaFase);
        if (proximaJaGerada) return;

        List<Selecao> vencedores = jogosFase.stream()
            .map(this::getVencedor)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        if (proximaFase == Fase.TERCEIRO_LUGAR) {
            List<Selecao> perdedores = jogosFase.stream()
                .map(this::getPerdedor)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

            if (perdedores.size() == 2) {
                jogoRepository.save(criarJogo(perdedores.get(0), perdedores.get(1), Fase.TERCEIRO_LUGAR));
                jogoRepository.save(criarJogo(vencedores.get(0), vencedores.get(1), Fase.FINAL));
                System.out.println("Final e Terceiro Lugar gerados!");
            }
            return;
        }

        List<Jogo> proximosJogos = new ArrayList<>();
        for (int i = 0; i < vencedores.size() - 1; i += 2) {
            proximosJogos.add(criarJogo(vencedores.get(i), vencedores.get(i + 1), proximaFase));
        }

        jogoRepository.saveAll(proximosJogos);
        System.out.println(proximaFase + " gerada com " + proximosJogos.size() + " jogos!");
    }

    private Fase proximaFase(Fase faseAtual) {
        return switch (faseAtual) {
            case DEZESSEIS_AVOS -> Fase.OITAVAS;
            case OITAVAS -> Fase.QUARTAS;
            case QUARTAS -> Fase.SEMIFINAL;
            case SEMIFINAL -> Fase.TERCEIRO_LUGAR;
            default -> null;
        };
    }

    private Selecao getVencedor(Jogo jogo) {
        if (!jogo.getEncerrado()) return null;
        if (jogo.getGolsCasa() > jogo.getGolsVisitante()) return jogo.getSelecaoCasa();
        if (jogo.getGolsVisitante() > jogo.getGolsCasa()) return jogo.getSelecaoVisitante();
        return jogo.getSelecaoCasa().getForca() >= jogo.getSelecaoVisitante().getForca()
            ? jogo.getSelecaoCasa() : jogo.getSelecaoVisitante();
    }

    private Selecao getPerdedor(Jogo jogo) {
        Selecao vencedor = getVencedor(jogo);
        if (vencedor == null) return null;
        return vencedor.equals(jogo.getSelecaoCasa())
            ? jogo.getSelecaoVisitante() : jogo.getSelecaoCasa();
    }

    private Jogo criarJogo(Selecao casa, Selecao visitante, Fase fase) {
        return Jogo.builder()
            .selecaoCasa(casa)
            .selecaoVisitante(visitante)
            .golsCasa(0)
            .golsVisitante(0)
            .fase(fase)
            .rodada(1)
            .encerrado(false)
            .temProrrogacao(false)
            .temPenaltis(false)
            .build();
    }
}