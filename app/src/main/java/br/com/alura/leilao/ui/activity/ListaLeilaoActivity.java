package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class ListaLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(this, leiloesDeExemplo());
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListaLeilaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Leilao leilao) {
                Intent vaiParaLancesLeilao = new Intent(ListaLeilaoActivity.this, LancesLeilaoActivity.class);
                vaiParaLancesLeilao.putExtra("leilao", leilao);
                startActivity(vaiParaLancesLeilao);
            }
        });
    }

    private List<Leilao> leiloesDeExemplo() {
        Leilao console = new Leilao("Console");
        console.add(new Lance(new Usuario("Luiz"), 500.0));
        console.add(new Lance(new Usuario("Luiz"), 300.0));
        console.add(new Lance(new Usuario("Luiz"), 900.0));
        console.add(new Lance(new Usuario("Luiz"), 1900.0));
        console.add(new Lance(new Usuario("Luiz"), 1780.0));


        Leilao bmx = new Leilao("BMX");
        bmx.add(new Lance(new Usuario("Luiz"), 500.0));
        bmx.add(new Lance(new Usuario("Luiz"), 800.0));
        bmx.add(new Lance(new Usuario("Luiz"), 900.0));
        bmx.add(new Lance(new Usuario("Luiz"), 5900.0));
        bmx.add(new Lance(new Usuario("Luiz"), 9800.0));

        Leilao mtb = new Leilao("Caloi Elite 30");
        mtb.add(new Lance(new Usuario("Luiz"), 5000.0));
        mtb.add(new Lance(new Usuario("Luiz"), 3000.0));
        mtb.add(new Lance(new Usuario("Luiz"), 1900.0));
        mtb.add(new Lance(new Usuario("Luiz"), 11000.0));
        mtb.add(new Lance(new Usuario("Luiz"), 2780.0));

        return new ArrayList<>(Arrays.asList(
                console, bmx, mtb
        ));
    }

}
