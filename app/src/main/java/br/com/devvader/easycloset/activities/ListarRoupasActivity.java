package br.com.devvader.easycloset.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.RoupaEntity;
import br.com.devvader.easycloset.recursos.IRoupaRepository;
import br.com.devvader.easycloset.recursos.RoupaRepository;

public class ListarRoupasActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_LISTAR_ROUPAS = "Listar Roupas";

    private IRoupaRepository roupaRepository = new RoupaRepository();
    private ListView enderecoDaListaDeRoupas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_roupas);
    }

    @Override
    protected void onResume() {
        super.onResume();

        colocarTituloNaTela();
        mapearEnderecoDaListaDeRoupas();
        mostrarListaDeRoupasNaTela();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_LISTAR_ROUPAS);
        }

        private void mapearEnderecoDaListaDeRoupas() {
            enderecoDaListaDeRoupas = findViewById(R.id.listView_listaDeRoupas);
        }

        private void mostrarListaDeRoupasNaTela() {

            String[] listaDeTipos = getResources().getStringArray(R.array.listaDeTiposDeRoupas);
            String[] listaDeCores = getResources().getStringArray(R.array.listaDeCores);
            String[] listaDeTamanhos = getResources().getStringArray(R.array.listaDeTamanhosDeRoupa);
            String[] listaDeTecidos = getResources().getStringArray(R.array.listaDeTecidos);

            List<RoupaEntity> listaDeRoupas = new ArrayList<>();

            for(int contador = 0; contador < 10; contador++) {
                listaDeRoupas.add(new RoupaEntity(
                        listaDeTipos[contador],
                        listaDeCores[contador],
                        listaDeTamanhos[contador],
                        listaDeTecidos[contador]));
            }

            enderecoDaListaDeRoupas.setAdapter(new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    listaDeRoupas)
            );
        }

            private List<RoupaEntity> buscarListaDeRoupasNoRepository() {
                return roupaRepository.buscarTodasPecasDeRoupa();
            }
}