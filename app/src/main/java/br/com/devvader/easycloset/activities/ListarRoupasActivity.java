package br.com.devvader.easycloset.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.RoupaEntity;
import br.com.devvader.easycloset.domain.adapters.RoupaAdapter;
import br.com.devvader.easycloset.recursos.IRoupaRepository;
import br.com.devvader.easycloset.recursos.RoupaRepository;

public final class ListarRoupasActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_LISTAR_ROUPAS = "Listar Roupas";
    public static final String ROUPA = "Roupa";

    private IRoupaRepository roupaRepository = new RoupaRepository();
    private ListView enderecoDaListaDeRoupas;
    private RoupaEntity roupa;
    private Intent ponteEntreListarAndCadastrar;

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_roupas);
    }

    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();

        mapearEnderecoDaListaDeRoupas();
        mostrarListaDeRoupasNaTelaComAdapterCustomizado();

        ativarCliqueNosItensDalistaParaEnviarMensagem();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_LISTAR_ROUPAS);
        }

        private void mapearEnderecoDaListaDeRoupas() {
            enderecoDaListaDeRoupas = findViewById(R.id.listView_listaDeRoupas);
        }

        private void mostrarListaDeRoupasNaTelaComAdapterCustomizado() {
            RoupaAdapter roupaAdapter = new RoupaAdapter(this, buscarListaDeRoupasNoRepository());
            enderecoDaListaDeRoupas.setAdapter(roupaAdapter);
        }

            private List<RoupaEntity> buscarListaDeRoupasNoRepository() {
                return roupaRepository.buscarTodasPecasDeRoupa();
            }

//        private void popularListaDeRoupas() {
//            String[] listaDeTipos = getResources().getStringArray(R.array.listaDeTiposDeRoupas);
//            String[] listaDeCores = getResources().getStringArray(R.array.listaDeCores);
//            String[] listaDeTamanhos = getResources().getStringArray(R.array.listaDeTamanhosDeRoupa);
//            String[] listaDeTecidos = getResources().getStringArray(R.array.listaDeTecidos);
//
//            listaDeRoupas = new ArrayList<>();
//
//            for(int contador = 0; contador < 10; contador++) {
//                listaDeRoupas.add(new RoupaEntity(
//                        listaDeTipos[contador],
//                        listaDeTamanhos[contador],
//                        listaDeCores[contador],
//                        listaDeTecidos[contador]));
//            }
//        }

        private void ativarCliqueNosItensDalistaParaEnviarMensagem() {
            enderecoDaListaDeRoupas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    roupa = (RoupaEntity) parent.getItemAtPosition(position);

                    gerarMensagemDeRoupaEscolhida();
                    gerarLogDaRoupaEscolhida(position);
                    criarPonteDaTelaListarRoupasParaTelaCadastrarRoupas();
                    guardarRoupaEscolhidaParaEnviarParaEditarNaTelaCadastrarRoupas();
                    abrirTelaDeCadastrarRoupasParaEditarRoupas();
                }
            });
        }

            private void gerarMensagemDeRoupaEscolhida() {
                Toast.makeText(getApplicationContext(),
                        roupa.getTipo().concat(" ").concat(roupa.getCorPrincipal()),
                        Toast.LENGTH_SHORT)
                        .show();
            }

            private void gerarLogDaRoupaEscolhida(int posicao) {
                Log.i("Roupa:", " " +
                        roupa.getTipo().concat(" ").concat(roupa.getCorPrincipal()) +
                        " - na posição: " + posicao);
            }

            private void criarPonteDaTelaListarRoupasParaTelaCadastrarRoupas() {
                ponteEntreListarAndCadastrar = new Intent(
                        ListarRoupasActivity.this, CadastrarRoupasActivity.class);
            }

            private void guardarRoupaEscolhidaParaEnviarParaEditarNaTelaCadastrarRoupas() {
                ponteEntreListarAndCadastrar.putExtra(ROUPA, roupa);
            }

            private void abrirTelaDeCadastrarRoupasParaEditarRoupas() {
                startActivity(ponteEntreListarAndCadastrar);
            }
}