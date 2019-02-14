package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void add(Lance lance) {
        double valor = lance.getValor();

        if (!eUmLanceValido(lance)) {
            return;
        }

        lances.add(lance);

        if (ePrimeiroLance(valor)) {
            return;
        }

        calculaMaiorLance(valor);
        calculaMenorLance(valor);
    }

    private boolean ePrimeiroLance(double valor) {
        if (lances.size() == 1) {
            menorLance = valor;
            maiorLance = valor;
            return true;
        }
        return false;
    }


    private boolean eUmLanceValido(Lance lance) {
        if (valorLanceEmaiorQueUltimoLance(lance)) {
            return false;
        }

        if (lanceAtualIgualUltimoLance(lance)) {
            return false;
        }

        if (quantidadeDeLancesPorUsuarioEmairQueCinco(lance)) {
            return false;
        }

        return true;
    }

    private boolean valorLanceEmaiorQueUltimoLance(Lance lance) {
        return maiorLance > lance.getValor();
    }

    private boolean lanceAtualIgualUltimoLance(Lance lance) {
        if (lances.isEmpty()) {
            return false;
        }

        Usuario usuarioUltimoLance = lances.get(lances.size() - 1).getUsuario();
        if (usuarioUltimoLance.equals(lance.getUsuario())) {
            return true;
        }

        return false;
    }

    private boolean quantidadeDeLancesPorUsuarioEmairQueCinco(Lance lance) {
        if (lances.isEmpty() || lances.size() < 10) {
            return false;
        }

        int limiteDeLances = 5;
        int lancesDoMesmoUsuario = 0;
        Usuario usuario = lance.getUsuario();

        for (Lance l : lances) {
            if (usuario.equals(l.getUsuario())) {
                lancesDoMesmoUsuario++;
            }

            if (lancesDoMesmoUsuario == limiteDeLances) {
                return true;
            }
        }

        return false;
    }


    private void calculaMenorLance(double valor) {
        if (valor < menorLance) {
            menorLance = valor;
        }
    }

    private void calculaMaiorLance(double valor) {
        if (valor > maiorLance) {
            maiorLance = valor;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public List<Lance> getTresMaioresLances() {
        Collections.sort(lances);
        int size = lances.size();
        return lances.subList(0, size >= 3 ? 3 : size);
    }

    public int getQuantidadeDeLances() {
        return lances.size();
    }
}
