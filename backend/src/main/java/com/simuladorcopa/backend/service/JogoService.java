package com.simuladorcopa.backend.service;

import com.simuladorcopa.backend.model.*;
import com.simuladorcopa.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class JogoService {

    private final JogoRepository jogoRepository;
    private final ClassificacaoGrupoRepository classificacaoGrupoRepository;
    private final MataMataService mataMataService;

    public List<Jogo> listarTodos() {
        return jogoRepository.findAll();
    }

    public Jogo simular(Long jogoId) {
        Jogo jogo = jogoRepository.findById(jogoId)
            .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        if (jogo.getEncerrado()) {
            throw new RuntimeException("Jogo já foi encerrado");
        }

        int forcaCasa = jogo.getSelecaoCasa().getForca();
        int forcaVisitante = jogo.getSelecaoVisitante().getForca();

        int golsCasa = gerarGols(forcaCasa, forcaVisitante);
        int golsVisitante = gerarGols(forcaVisitante, forcaCasa);

        // No mata-mata não pode ter empate — vai para prorrogação/pênaltis
        if (jogo.getFase() != Fase.GRUPOS && golsCasa == golsVisitante) {
            int[] penaltis = simularPenaltis(forcaCasa, forcaVisitante);
            jogo.setTemProrrogacao(true);
            jogo.setTemPenaltis(true);
            jogo.setGolsCasa(golsCasa);
            jogo.setGolsVisitante(golsVisitante);
            jogo.setEncerrado(true);
            jogoRepository.save(jogo);

            // Atualiza classificação com o vencedor nos pênaltis
            if (penaltis[0] > penaltis[1]) {
                atualizarClassificacaoMataMata(jogo.getSelecaoCasa(), jogo.getSelecaoVisitante());
            } else {
                atualizarClassificacaoMataMata(jogo.getSelecaoVisitante(), jogo.getSelecaoCasa());
            }

            mataMataService.avancarFase(jogo.getFase());
            return jogo;
        }

        return registrarResultado(jogo, golsCasa, golsVisitante);
    }

    public Jogo registrarResultado(Long jogoId, int golsCasa, int golsVisitante) {
        Jogo jogo = jogoRepository.findById(jogoId)
            .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        if (jogo.getEncerrado()) {
            throw new RuntimeException("Jogo já foi encerrado");
        }

        return registrarResultado(jogo, golsCasa, golsVisitante);
    }

    private int gerarGols(int forcaAtaque, int forcaDefesa) {
        Random random = new Random();
        double diferenca = (forcaAtaque - forcaDefesa) / 100.0;
        double mediaGols = 1.2 + diferenca;
        mediaGols = Math.max(0.3, Math.min(3.5, mediaGols));

        double limite = Math.exp(-mediaGols);
        int gols = 0;
        double produto = random.nextDouble();

        while (produto > limite) {
            produto *= random.nextDouble();
            gols++;
        }

        return Math.min(gols, 8);
    }

    private int[] simularPenaltis(int forcaCasa, int forcaVisitante) {
        Random random = new Random();
        int golsCasa = 0;
        int golsVisitante = 0;

        // 5 cobranças cada
        for (int i = 0; i < 5; i++) {
            double chanceCasa = 0.7 + (forcaCasa - forcaVisitante) / 1000.0;
            double chanceVisitante = 0.7 + (forcaVisitante - forcaCasa) / 1000.0;
            if (random.nextDouble() < chanceCasa) golsCasa++;
            if (random.nextDouble() < chanceVisitante) golsVisitante++;
        }

        // Morte súbita se empatado
        while (golsCasa == golsVisitante) {
            double chanceCasa = 0.7 + (forcaCasa - forcaVisitante) / 1000.0;
            double chanceVisitante = 0.7 + (forcaVisitante - forcaCasa) / 1000.0;
            if (random.nextDouble() < chanceCasa) golsCasa++;
            if (random.nextDouble() < chanceVisitante) golsVisitante++;
        }

        return new int[]{golsCasa, golsVisitante};
    }

    private void atualizarClassificacaoMataMata(Selecao vencedor, Selecao perdedor) {
        // No mata-mata só registramos quem venceu — sem pontos
        // Apenas para controle interno se necessário
    }

    private Jogo registrarResultado(Jogo jogo, int golsCasa, int golsVisitante) {
        jogo.setGolsCasa(golsCasa);
        jogo.setGolsVisitante(golsVisitante);
        jogo.setEncerrado(true);
        jogoRepository.save(jogo);

        if (jogo.getFase() == Fase.GRUPOS) {
            atualizarClassificacao(jogo.getSelecaoCasa(), golsCasa, golsVisitante);
            atualizarClassificacao(jogo.getSelecaoVisitante(), golsVisitante, golsCasa);
            mataMataService.gerarMataMata();
        } else {
            mataMataService.avancarFase(jogo.getFase());
        }

        return jogo;
    }

    private void atualizarClassificacao(Selecao selecao, int golsPro, int golsContra) {
        ClassificacaoGrupo c = classificacaoGrupoRepository
            .findBySelecao(selecao)
            .orElseThrow(() -> new RuntimeException("Classificação não encontrada"));

        c.setGolsPro(c.getGolsPro() + golsPro);
        c.setGolsContra(c.getGolsContra() + golsContra);
        c.setSaldoGols(c.getGolsPro() - c.getGolsContra());

        if (golsPro > golsContra) {
            c.setPontos(c.getPontos() + 3);
            c.setVitorias(c.getVitorias() + 1);
        } else if (golsPro == golsContra) {
            c.setPontos(c.getPontos() + 1);
            c.setEmpates(c.getEmpates() + 1);
        } else {
            c.setDerrotas(c.getDerrotas() + 1);
        }

        classificacaoGrupoRepository.save(c);
    }
}