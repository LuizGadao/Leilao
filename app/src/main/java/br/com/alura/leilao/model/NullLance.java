package br.com.alura.leilao.model;

public class NullLance extends Lance {

    private NullLance(Usuario usuario, double valor) {
        super(usuario, valor);
    }

    public NullLance() {
        this(new Usuario("Null"), 0.0);
    }

}
