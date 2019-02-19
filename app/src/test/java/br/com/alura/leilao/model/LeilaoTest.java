package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLance;
import br.com.alura.leilao.exception.LancesSeguidosMesmoUsuarioException;
import br.com.alura.leilao.exception.LimiteDeLancesPorUsuarioException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LeilaoTest {

    private static final double DELTA = 0.0001;
    private final Leilao PS4 = new Leilao("PS4");
    private final Usuario LUIZ = new Usuario("Luiz");
    private final Usuario ANA = new Usuario("Ana");

    @Test
    public void verificaDescricao() {
        assertThat(PS4.getDescricao(), is(equalTo("PS4")));
    }

    @Test
    public void verificaMenorLance() {
        PS4.add(new Lance(LUIZ, 90.0));
        PS4.add(new Lance(ANA, 900.0));
        PS4.add(new Lance(LUIZ, 1100.0));
        PS4.add(new Lance(ANA, 1500.0));
        PS4.add(new Lance(LUIZ, 1900.0));

        assertThat(PS4.getMenorLance(), closeTo(90.0, DELTA));
    }

    @Test
    public void verificaTresMaioresLances() {
        PS4.add(new Lance(LUIZ, 500.0));
        PS4.add(new Lance(ANA, 900.0));
        PS4.add(new Lance(LUIZ, 2900.0));
        PS4.add(new Lance(ANA, 5000.0));
        PS4.add(new Lance(LUIZ, 9000.0));

        List<Lance> tresMaioresLances = PS4.getTresMaioresLances();
        assertThat(tresMaioresLances, contains(
                new Lance(LUIZ, 9000.0),
                new Lance(ANA, 5000.0),
                new Lance(LUIZ, 2900.0)
        ));
    }

    @Test
    public void verificaTresMaioresLancesQuandoTemMaisDeTresLances() {
        PS4.add(new Lance(LUIZ, 500.0));
        PS4.add(new Lance(ANA, 900.0));
        PS4.add(new Lance(LUIZ, 2900.0));
        PS4.add(new Lance(ANA, 5000.0));
        PS4.add(new Lance(LUIZ, 9000.0));

        List<Lance> tresMaioresLances = PS4.getTresMaioresLances();
        assertThat(tresMaioresLances, Matchers.<Lance>hasSize(3));
        assertThat(tresMaioresLances, contains(
                new Lance(LUIZ, 9000.0),
                new Lance(ANA, 5000.0),
                new Lance(LUIZ, 2900.0)
        ));

        /*assertThat(tresMaioresLances, both(Matchers.<Lance>hasSize(3))
        .and(contains(
                new Lance(LUIZ, 9000.0),
                new Lance(ANA, 5000.0),
                new Lance(LUIZ, 2900.0)
        )));*/
    }

    @Test
    public void verificaTresMaioresLancesQuandoNaoInsereLances() {
        assertThat(PS4.getMenorLance(), closeTo(0.0, DELTA));
    }

    @Test
    public void verificaTresMaioresLancesMasInsereApenasUmLance() {
        PS4.add(new Lance(LUIZ, 2900.0));
        assertThat(PS4.getTresMaioresLances(), Matchers.<Lance>hasSize(1));
        assertThat(PS4.getTresMaioresLances().get(0).getValor(), closeTo(2900.0, DELTA));
    }

    @Test
    public void verificaTresMaioresLancesMasInsereApenasDoisLances() {
        PS4.add(new Lance(LUIZ, 2900.0));
        PS4.add(new Lance(ANA, 3900.0));

        List<Lance> tresMaioresLances = PS4.getTresMaioresLances();

        assertThat(tresMaioresLances, hasSize(2));
        assertThat(tresMaioresLances.get(0).getValor(), closeTo(3900.0, DELTA));
        assertThat(tresMaioresLances.get(1).getValor(), closeTo(2900.0, DELTA));
    }

    @Test
    public void verificaMenorLanceQaundoNaoHouverLances() {
        double menorLance = PS4.getMenorLance();
        assertThat(menorLance, closeTo(0.0, DELTA));
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