package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void add(Lance lance) {
        lances.add(lance);

        double valor = lance.getValor();
        setMaiorLance(valor);
        setMenorLance(valor);
    }

    private void setMenorLance(double valor) {
        if (valor < menorLance) {
            menorLance = valor;
        }
    }

    private void setMaiorLance(double valor) {
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
        return lances.subList(0, 3);
    }
}
