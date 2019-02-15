package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLance;
import br.com.alura.leilao.exception.LancesSeguidosMesmoUsuarioException;
import br.com.alura.leilao.exception.LimiteDeLancesPorUsuarioException;

import static org.junit.Assert.*;

public class LeilaoTest {

    private static final double DELTA = 0.0001;
    private final Leilao PS4 = new Leilao("PS4");
    private final Usuario LUIZ = new Usuario("Luiz");
    private final Usuario ANA = new Usuario("Ana");

    @Test
    public void verificaDescricao() {
        assertEquals("PS4", PS4.getDescricao());
    }

    @Test
    public void verificaMenorLance() {
        PS4.add(new Lance(LUIZ, 90.0));
        PS4.add(new Lance(ANA, 900.0));
        PS4.add(new Lance(LUIZ, 1100.0));
        PS4.add(new Lance(ANA, 1500.0));
        PS4.add(new Lance(LUIZ, 1900.0));

        assertEquals(90.0, PS4.getMenorLance(), DELTA);
    }

    @Test
    public void verificaTresMaioresLances() {
        PS4.add(new Lance(LUIZ, 500.0));
        PS4.add(new Lance(ANA, 900.0));
        PS4.add(new Lance(LUIZ, 2900.0));
        PS4.add(new Lance(ANA, 5000.0));
        PS4.add(new Lance(LUIZ, 9000.0));

        List<Lance> tresMaioresLances = PS4.getTresMaioresLances();
        assertEquals(9000.0, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(5000.0, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(2900.0, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void verificaTresMaioresLancesQuandoTemMaisDeTresLances() {
        PS4.add(new Lance(LUIZ, 500.0));
        PS4.add(new Lance(ANA, 900.0));
        PS4.add(new Lance(LUIZ, 2900.0));
        PS4.add(new Lance(ANA, 5000.0));
        PS4.add(new Lance(LUIZ, 9000.0));

        List<Lance> tresMaioresLances = PS4.getTresMaioresLances();
        assertEquals(3, tresMaioresLances.size());
        assertEquals(9000.0, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(5000.0, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(2900.0, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void verificaTresMaioresLancesQuandoNaoInsereLances() {
        assertEquals(0, PS4.getTresMaioresLances().size());
    }

    @Test
    public void verificaTresMaioresLancesMasInsereApenasUmLance() {
        PS4.add(new Lance(LUIZ, 2900.0));
        assertEquals(1, PS4.getTresMaioresLances().size());
        assertEquals(2900.0, PS4.getTresMaioresLances().get(0).getValor(), DELTA);
    }

    @Test
    public void verificaTresMaioresLancesMasInsereApenasDoisLances() {
        PS4.add(new Lance(LUIZ, 2900.0));
        PS4.add(new Lance(ANA, 3900.0));

        assertEquals(2, PS4.getTresMaioresLances().size());
        assertEquals(3900.0, PS4.getTresMaioresLances().get(0).getValor(), DELTA);
        assertEquals(2900.0, PS4.getTresMaioresLances().get(1).getValor(), DELTA);
    }

    @Test
    public void verificaMenorLanceQaundoNaoHouverLances() {
        double menorLance = PS4.getMenorLance();
        assertEquals(0.0, menorLance, DELTA);
    }

    @Test
    public void verificaMaiorLanceQaundoNaoHouverLances() {
        double menorLance = PS4.getMaiorLance();
        assertEquals(0.0, menorLance, DELTA);
    }

    @Test(expected = LanceMenorQueUltimoLance.class)
    public void verificaSeAddMenorLance() {
        PS4.add(new Lance(LUIZ, 900.0));
        PS4.add(new Lance(ANA, 2900.0));
        PS4.add(new Lance(LUIZ, 1900.0));
    }

    @Test(expected = LancesSeguidosMesmoUsuarioException.class)
    public void verificaDoisLancesSeguidosDoMesmoUsuario() {
        PS4.add(new Lance(LUIZ, 9900.0));
        PS4.add(new Lance(LUIZ, 10000.0));
    }

    @Test(expected = LimiteDeLancesPorUsuarioException.class)
    public void naoDeveTerCincoLancesDoMesmoUsuario() {
        for (int i = 1; i < 12; i++) {
            Lance lance = new Lance(i % 2 == 0 ? LUIZ : ANA, i * 500.0);
            PS4.add(lance);
        }
    }
}