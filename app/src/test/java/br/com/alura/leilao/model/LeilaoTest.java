package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    private static final double DELTA = 0.0001;
    private final Leilao PS4 = new Leilao("PS4");
    private final Usuario LUIZ = new Usuario("Luiz");

    @Test
    public void verificaDescricao() {
        assertEquals("PS4", PS4.getDescricao());
    }

    @Test
    public void verificaMaiorLanceValoresAleatorios() {
        PS4.add(new Lance(LUIZ, 900.0));
        PS4.add(new Lance(LUIZ, 500.0));
        PS4.add(new Lance(LUIZ, 1900.0));
        PS4.add(new Lance(LUIZ, 1500.0));
        PS4.add(new Lance(LUIZ, 1100.0));

        assertEquals(1900.0, PS4.getMaiorLance(), DELTA);
    }

    @Test
    public void verificaMaiorLanceValoresCrecentes() {
        PS4.add(new Lance(LUIZ, 90.0));
        PS4.add(new Lance(LUIZ, 500.0));
        PS4.add(new Lance(LUIZ, 1900.0));
        PS4.add(new Lance(LUIZ, 2500.0));
        PS4.add(new Lance(LUIZ, 11000.0));

        assertEquals(11000.0, PS4.getMaiorLance(), DELTA);
    }

    @Test
    public void verificaMaiorLanceValoresDecrecentes() {
        PS4.add(new Lance(LUIZ, 9000.0));
        PS4.add(new Lance(LUIZ, 5000.0));
        PS4.add(new Lance(LUIZ, 2900.0));
        PS4.add(new Lance(LUIZ, 2500.0));
        PS4.add(new Lance(LUIZ, 1100.0));

        assertEquals(9000.0, PS4.getMaiorLance(), DELTA);
    }

    @Test
    public void verificaMenorLanceValoresAleatorios() {
        PS4.add(new Lance(LUIZ, 900.0));
        PS4.add(new Lance(LUIZ, 500.0));
        PS4.add(new Lance(LUIZ, 1900.0));
        PS4.add(new Lance(LUIZ, 1500.0));
        PS4.add(new Lance(LUIZ, 1100.0));

        assertEquals(500.0, PS4.getMenorLance(), DELTA);
    }

    @Test
    public void verificaMenorLanceValoresCrecentes() {
        PS4.add(new Lance(LUIZ, 90.0));
        PS4.add(new Lance(LUIZ, 500.0));
        PS4.add(new Lance(LUIZ, 1900.0));
        PS4.add(new Lance(LUIZ, 2500.0));
        PS4.add(new Lance(LUIZ, 11000.0));

        assertEquals(90.0, PS4.getMenorLance(), DELTA);
    }

    @Test
    public void verificaMenorLanceValoresDecrecentes() {
        PS4.add(new Lance(LUIZ, 9000.0));
        PS4.add(new Lance(LUIZ, 5000.0));
        PS4.add(new Lance(LUIZ, 2900.0));
        PS4.add(new Lance(LUIZ, 2500.0));
        PS4.add(new Lance(LUIZ, 1100.0));

        assertEquals(1100.0, PS4.getMenorLance(), DELTA);
    }

    @Test
    public void verificaTresMaioresLances() {
        PS4.add(new Lance(LUIZ, 2900.0));
        PS4.add(new Lance(LUIZ, 9000.0));
        PS4.add(new Lance(LUIZ, 2500.0));
        PS4.add(new Lance(LUIZ, 5000.0));
        PS4.add(new Lance(LUIZ, 1100.0));

        List<Lance> tresMaioresLances = PS4.getTresMaioresLances();
        assertEquals(3, tresMaioresLances.size());

        assertEquals(9000.0, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(5000.0, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(2900.0, tresMaioresLances.get(2).getValor(), DELTA);
    }
}